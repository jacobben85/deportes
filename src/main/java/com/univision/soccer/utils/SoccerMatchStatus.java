package com.univision.soccer.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Soccer Match status
 */
public enum SoccerMatchStatus {
    FULL("FULL", "post-event"),
    LIVE("LIVE", "mid-event"),
    HALFTIME("HALF-TIME", "intermission"),
    PREMATCH("PRE-MATCH", "pre-event"),
    POSTPONED("POSTPONED", "postponed"),
    ABANDONED("ABANDONED", "canceled");

    private String value;

    private String sportsMLEventStatus;

    private SoccerMatchStatus(String value, String sportsMLEventStatus) {
        this.value = value;
        this.sportsMLEventStatus = sportsMLEventStatus;
    }

    public String getValue() {
        return value;
    }

    public String getSportsMLEventStatus() {
        return sportsMLEventStatus;
    }

    @Override
    public String toString() {
        return value;
    }

    public static SoccerMatchStatus fromString(String soccerMatchStatus) {

        if (!StringUtils.isBlank(soccerMatchStatus)) {
            for (SoccerMatchStatus sms : SoccerMatchStatus.values()) {
                if (soccerMatchStatus.equals(sms.getValue())) {
                    return sms;
                }
            }
        }

        return null;
    }

    public static SoccerMatchStatus fromFixturesPeriod(String period) {

        SoccerMatchStatus soccerMatchStatus = null;
        if (!StringUtils.isBlank(period)) {
            if (period.equals("PreMatch")) {
                soccerMatchStatus = SoccerMatchStatus.PREMATCH;
            } else if (period.equals("FullTime")) {
                soccerMatchStatus = SoccerMatchStatus.FULL;
            } else if (period.equals("Postponed")) {
                soccerMatchStatus = SoccerMatchStatus.POSTPONED;
            } else if (period.equals("Abandoned")) {
                soccerMatchStatus = SoccerMatchStatus.ABANDONED;
            } else if (period.equals("Live")) {
                soccerMatchStatus = SoccerMatchStatus.LIVE;
            }
        }

        return soccerMatchStatus;
    }
}