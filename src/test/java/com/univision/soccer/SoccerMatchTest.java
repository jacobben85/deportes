package com.univision.soccer;

import com.univision.soccer.utils.SoccerMatchPeriod;
import com.univision.soccer.utils.SoccerMatchStatus;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

/**
 * Soccer match test scripts
 */
public class SoccerMatchTest {

    @Test
    public void testRenderMatchDate() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();
        String date = SoccerMatch.renderMatchDate(soccerMatch, zone, locale);
        System.out.println(date);
    }

    @Test
    public void testRenderMatchTime35Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.FIRST_HALF);
        Date dateNow = new Date();

        Date date35MinAgo = new Date(dateNow.getTime() - (35 * 60000));

        soccerMatch.setMatchPeriod(date35MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("35'", time);
    }

    @Test
    public void testRenderMatchTime47Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.FIRST_HALF);
        Date dateNow = new Date();

        Date date47MinAgo = new Date(dateNow.getTime() - (47 * 60000));

        soccerMatch.setMatchPeriod(date47MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("45' + 2'", time);
    }

    @Test
    public void testRenderMatchTimeSecondHalf47Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.SECOND_HALF);
        Date dateNow = new Date();

        Date date15MinAgo = new Date(dateNow.getTime() - (15 * 60000));

        soccerMatch.setMatchPeriod(date15MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("60'", time);
    }
}