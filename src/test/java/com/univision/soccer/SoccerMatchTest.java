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

    Date dateNow = new Date();
    Date date2MinAgo = new Date(dateNow.getTime() - (2 * 60000));
    Date date15MinAgo = new Date(dateNow.getTime() - (15 * 60000));
    Date date17MinAgo = new Date(dateNow.getTime() - (17 * 60000));
    Date date35MinAgo = new Date(dateNow.getTime() - (35 * 60000));
    Date date47MinAgo = new Date(dateNow.getTime() - (47 * 60000));
    Date date60MinAgo = new Date(dateNow.getTime() - (60 * 60000));
    Date date92MinAgo = new Date(dateNow.getTime() - (92 * 60000));
    Date date107MinAgo = new Date(dateNow.getTime() - (107 * 60000));
    Date date122MinAgo = new Date(dateNow.getTime() - (122 * 60000));

    DateTimeZone zone = DateTimeZone.forID("Asia/Singapore");
    Locale locale = Locale.getDefault();

    @Test
    public void testRenderMatchDate() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        String date = SoccerMatch.renderMatchDate(soccerMatch, zone, locale);
        System.out.printf(date);
    }

    @Test
    public void testRenderMatchTime35Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.FIRST_HALF);

        soccerMatch.setMatchPeriod(date35MinAgo);
        soccerMatch.setMatchTime(date35MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("35'", time);
    }

    @Test
    public void testRenderMatchTime47Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.FIRST_HALF);

        soccerMatch.setMatchPeriod(date47MinAgo);
        soccerMatch.setMatchTime(date47MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("45' + 2'", time);
    }

    @Test
    public void testRenderMatchTimeSecondHalf47Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.SECOND_HALF);

        soccerMatch.setMatchPeriod(date2MinAgo);
        soccerMatch.setMatchTime(date47MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("47'", time);
    }

    @Test
    public void testRenderMatchTimeSecondHalf60Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.SECOND_HALF);

        soccerMatch.setMatchPeriod(date15MinAgo);
        soccerMatch.setMatchTime(date60MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("60'", time);
    }

    @Test
    public void testRenderMatchTimeSecondHalf92Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.SECOND_HALF);

        soccerMatch.setMatchPeriod(date47MinAgo);
        soccerMatch.setMatchTime(date92MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("90' + 2'", time);
    }

    @Test
    public void testRenderMatchTimeExtraFirstHalf92Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_FIRST_HALF);

        soccerMatch.setMatchPeriod(date2MinAgo);
        soccerMatch.setMatchTime(date92MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("92'", time);
    }

    @Test
    public void testRenderMatchTimeExtraFirstHalf107Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_FIRST_HALF);

        soccerMatch.setMatchPeriod(date17MinAgo);
        soccerMatch.setMatchTime(date107MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("105' + 2'", time);
    }

    @Test
    public void testRenderMatchTimeExtraSecondHalf107Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_SECOND_HALF);

        soccerMatch.setMatchPeriod(date2MinAgo);
        soccerMatch.setMatchTime(date107MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("107'", time);
    }

    @Test
    public void testRenderMatchTimeExtraSecondHalf122Min() throws Exception {
        SoccerMatch soccerMatch = new SoccerMatch();
        soccerMatch.setSoccerMatchStatus(SoccerMatchStatus.LIVE);
        soccerMatch.setSoccerMatchPeriod(SoccerMatchPeriod.EXTRA_SECOND_HALF);

        soccerMatch.setMatchPeriod(date17MinAgo);
        soccerMatch.setMatchTime(date122MinAgo);

        String time = SoccerMatch.renderMatchTime(soccerMatch, zone, locale);
        Assert.assertEquals("120' + 2'", time);
    }
}