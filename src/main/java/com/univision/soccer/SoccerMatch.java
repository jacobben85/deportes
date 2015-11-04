package com.univision.soccer;

import com.univision.soccer.utils.SoccerMatchPeriod;
import com.univision.soccer.utils.SoccerMatchStatus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * Soccer match
 */
public class SoccerMatch {

    private static String formatTime(Date timestamp, int threshold, int offset) {
        DateTime gameTimestamp = new DateTime(timestamp);
        DateTime currentTime = new DateTime();
        Duration duration = new Duration(gameTimestamp, currentTime);
        long minutes = duration.getStandardMinutes();
        StringBuilder minuteBuilder = new StringBuilder();
        if (minutes > threshold) {
            minuteBuilder.append(threshold + offset);
            minuteBuilder.append("' + ");
            minuteBuilder.append(minutes - threshold);
        } else {
            minuteBuilder.append(minutes + offset);
        }
        minuteBuilder.append("'");
        return minuteBuilder.toString();
    }

    /**
     * TODO: apply to Locale
     *
     * @param locale
     * @return the DateTimeFormatter for the locale. Never {@code null}
     */
    private static DateTimeFormatter getDateFormatterForLocale(Locale locale) {
        return DateTimeFormat.forPattern("dd MMM");
    }

    /**
     * TODO: apply to Locale
     *
     * @param locale
     * @return the DateTimeFormatter for the locale. Never {@code null}
     */
    private static DateTimeFormatter getTimeFormatterForLocale(Locale locale) {
        return DateTimeFormat.forPattern("h:mm a");
    }

    public static String renderMatchDate(SoccerMatch soccerMatch, DateTimeZone timezone, Locale locale) {
        DateTime matchDateTime = new DateTime(soccerMatch.getMatchTime());
        if (timezone != null) {
            matchDateTime = matchDateTime.withZone(timezone);
        }
        String date;
        if (isLiveStream(soccerMatch)) {
            date = "EN VIVO";
        } else {
            date = getDateFormatterForLocale(locale).print(matchDateTime);
        }
        return date;
    }

    public static String renderMatchTime(SoccerMatch soccerMatch, DateTimeZone timezone, Locale locale) {
        // TODO: Localize match time message.
        DateTime matchDateTime = new DateTime(soccerMatch.getMatchTime());
        if (timezone != null) {
            matchDateTime = matchDateTime.withZone(timezone);
        }
        SoccerMatchStatus status = soccerMatch.getSoccerMatchStatus();
        SoccerMatchPeriod period = soccerMatch.getSoccerMatchPeriod();
        String time = null;
        if (status == SoccerMatchStatus.PREMATCH && period == SoccerMatchPeriod.PREMATCH) {
            time = getTimeFormatterForLocale(locale).print(matchDateTime);
        } else if (status == SoccerMatchStatus.LIVE) {
            Date timeStamp = soccerMatch.getSoccerMatchPeriodTimeStamp();
            if (period == SoccerMatchPeriod.FIRST_HALF) {
                time = formatTime(timeStamp, 45, 0);
            } else if (period == SoccerMatchPeriod.SECOND_HALF) {
                time = formatTime(timeStamp, 90, 0);
            } else if (period == SoccerMatchPeriod.EXTRA_TIME) {
                time = "TE";
            } else if (period == SoccerMatchPeriod.EXTRA_FIRST_HALF) {
                time = formatTime(timeStamp, 105, 0);
            } else if (period == SoccerMatchPeriod.EXTRA_HALF_TIME) {
                time = "MT";
            } else if (period == SoccerMatchPeriod.EXTRA_SECOND_HALF) {
                time = formatTime(timeStamp, 120, 0);
            } else if (period == SoccerMatchPeriod.PENALTY_SHOOTOUT) {
                time = "PEN";
            }
        } else if (status == SoccerMatchStatus.HALFTIME && period == SoccerMatchPeriod.HALF_TIME) {
            time = "MT";
        } else if (status == SoccerMatchStatus.FULL) {
            if (period == SoccerMatchPeriod.FULL_TIME || period == SoccerMatchPeriod.POSTMATCH) {
                if (soccerMatch.getHomeTeamPenaltyScore() != null && soccerMatch.getAwayTeamPenaltyScore() != null) {
                    time = "FIN-PEN";
                } else if (Boolean.TRUE.equals(soccerMatch.getExtraTime())) {
                    time = "FIN-TE";
                } else {
                    time = "FIN";
                }
            }
        } else if (status == SoccerMatchStatus.POSTPONED) {
            time = "POS";
        } else if (status == SoccerMatchStatus.ABANDONED && period == SoccerMatchPeriod.ABANDONED) {
            time = "SUSP";
        }
        return time;
    }

    public static boolean isLiveStream(SoccerMatch soccerMatch) {
        SoccerMatchStatus status = soccerMatch.getSoccerMatchStatus();
        return Boolean.TRUE.equals(soccerMatch.getLiveStream()) && (status == SoccerMatchStatus.LIVE || status == SoccerMatchStatus.HALFTIME);
    }

    private SoccerMatchStatus getSoccerMatchStatus() {
        return soccerMatchStatus;
    }

    private SoccerMatchPeriod getSoccerMatchPeriod() {
        return soccerMatchPeriod;
    }

    private boolean getLiveStream() {
        return liveStream;
    }

    private Integer getHomeTeamPenaltyScore() {
        return homePenality;
    }

    private Integer getAwayTeamPenaltyScore() {
        return awayPenality;
    }

    private boolean getExtraTime() {
        return extraTime;
    }

    private Date getSoccerMatchPeriodTimeStamp() {
        return matchPeriod;
    }

    private Date getMatchTime() {
        return matchTime;
    }

    private SoccerMatchStatus soccerMatchStatus = SoccerMatchStatus.PREMATCH;
    private SoccerMatchPeriod soccerMatchPeriod = SoccerMatchPeriod.PREMATCH;
    private boolean liveStream = false;
    private Integer homePenality;
    private Integer awayPenality;
    private boolean extraTime = false;
    private Date matchPeriod = new Date();
    private Date matchTime = new Date();

    public SoccerMatch() {
    }

    public void setSoccerMatchStatus(SoccerMatchStatus soccerMatchStatus) {
        this.soccerMatchStatus = soccerMatchStatus;
    }

    public void setSoccerMatchPeriod(SoccerMatchPeriod soccerMatchPeriod) {
        this.soccerMatchPeriod = soccerMatchPeriod;
    }

    public void setLiveStream(boolean liveStream) {
        this.liveStream = liveStream;
    }

    public void setHomePenality(Integer homePenality) {
        this.homePenality = homePenality;
    }

    public void setAwayPenality(Integer awayPenality) {
        this.awayPenality = awayPenality;
    }

    public void setExtraTime(boolean extraTime) {
        this.extraTime = extraTime;
    }

    public void setMatchPeriod(Date matchPeriod) {
        this.matchPeriod = matchPeriod;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }
}
