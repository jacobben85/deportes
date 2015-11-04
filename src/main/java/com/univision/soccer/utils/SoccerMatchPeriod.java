package com.univision.soccer.utils;

import org.apache.commons.lang3.StringUtils;
/**
 * <ul>
 * <li>PreMatch - Match has not kicked off</li>
 * <li>First Half - Match is in the 1st half</li>
 * <li>Half Time - Match stopped for half time</li>
 * <li>Second Half - Match is in the 2nd half</li>
 * <li>Extra Time - Match is at full time and is about to start extra time</li>
 * <li>Extra First Half - Match is in the 1st extra half</li>
 * <li>Extra Half Time - Match is stopped for half time in extra time</li>
 * <li>Extra Second Half - Match is in the second extra half</li>
 * <li>Full Time - Match is finished in open play</li>
 * <li>Penalty Shootout - Match is in a penalty shootout</li>
 * <li>PostMatch - Match has ended</li>
 * <li>Abandoned - Match was called off or abandoned</li>
 * </ul>
 */
public enum SoccerMatchPeriod {
    PREMATCH("PreMatch", "pre-event", null),
    FIRST_HALF("First Half", "First half", 1),
    HALF_TIME("Half Time", "Half-time", 1),
    SECOND_HALF("Second Half", "Second half", 2),
    EXTRA_TIME("Extra Time", "Extra time", 2),
    EXTRA_FIRST_HALF("Extra First Half", "Extra-time first half", 3),
    EXTRA_HALF_TIME("Extra Half Time", "Extra-time half-time", 3),
    EXTRA_SECOND_HALF("Extra Second Half", "Extra-time second half", 3),
    FULL_TIME("Full Time", "Result", null),
    PENALTY_SHOOTOUT("Penalty Shootout", "Penalties live", 4),
    POSTMATCH("PostMatch", "post-event", null),
    ABANDONED("Abandoned", "Abandoned", null);

    private String value;
    private String note;
    private Integer periodValue;

    private SoccerMatchPeriod(String value, String note, Integer periodValue) {
        this.value = value;
        this.note = note;
        this.periodValue = periodValue;
    }

    public String getValue() {
        return value;
    }

    public String getNote() {
        return note;
    }

    public Integer getPeriodValue() {
        return periodValue;
    }

    @Override
    public String toString() {
        return value;
    }

    public static SoccerMatchPeriod fromString(String soccerMatchPeriod) {

        if (!StringUtils.isBlank(soccerMatchPeriod)) {
            for (SoccerMatchPeriod smp : SoccerMatchPeriod.values()) {
                if (soccerMatchPeriod.equals(smp.getValue())) {
                    return smp;
                }
            }
        }

        return null;
    }

    public static SoccerMatchPeriod fromFixturesPeriod(String period) {

        SoccerMatchPeriod soccerMatchPeriod = null;
        if (!StringUtils.isBlank(period)) {
            if (period.equals("PreMatch")) {
                soccerMatchPeriod = SoccerMatchPeriod.PREMATCH;
            } else if (period.equals("FullTime")) {
                soccerMatchPeriod = SoccerMatchPeriod.FULL_TIME;
            } else if (period.equals("Abandoned")) {
                soccerMatchPeriod = SoccerMatchPeriod.ABANDONED;
            }
        }

        return soccerMatchPeriod;
    }
}

