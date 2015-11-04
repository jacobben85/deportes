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
    public void testRenderMatchTimeSecondHalf60Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.SECOND_HALF);
        Date dateNow = new Date();

        Date date60MinAgo = new Date(dateNow.getTime() - (60 * 60000));

        soccerMatch.setMatchPeriod(date60MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        System.out.printf(time);
        Assert.assertEquals("60'", time);
    }

    @Test
    public void testRenderMatchTimeSecondHalf92Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.SECOND_HALF);
        Date dateNow = new Date();

        Date date60MinAgo = new Date(dateNow.getTime() - (92 * 60000));

        soccerMatch.setMatchPeriod(date60MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("90' + 2'", time);
    }

    @Test
    public void testRenderMatchTimeExtraFirstHalf92Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_FIRST_HALF);
        Date dateNow = new Date();

        Date date60MinAgo = new Date(dateNow.getTime() - (92 * 60000));

        soccerMatch.setMatchPeriod(date60MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("90' + 2'", time);
    }

    @Test
    public void testRenderMatchTimeExtraFirstHalf107Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_FIRST_HALF);
        Date dateNow = new Date();

        Date date107MinAgo = new Date(dateNow.getTime() - (107 * 60000));

        soccerMatch.setMatchPeriod(date107MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("105' + 2'", time);
    }

    @Test
    public void testRenderMatchTimeExtraSecondHalf107Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_SECOND_HALF);
        Date dateNow = new Date();

        Date date107MinAgo = new Date(dateNow.getTime() - (107 * 60000));

        soccerMatch.setMatchPeriod(date107MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("107'", time);
    }

    @Test
    public void testRenderMatchTimeExtraSecondHalf122Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_SECOND_HALF);
        Date dateNow = new Date();

        Date date122MinAgo = new Date(dateNow.getTime() - (122 * 60000));

        soccerMatch.setMatchPeriod(date122MinAgo);
        soccerMatch.setMatchTime(dateNow);

        DateTimeZone zone = DateTimeZone.forID("America/New_York");
        Locale locale = Locale.getDefault();

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("120' + 2'", time);
    }
}