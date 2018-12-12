/*
 * Copyright (C) 2016 100035
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.zs.ina.common;

/**
 *
 * @author 100035
 */
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 100018
 */
public class TimeAgoFunctions {

    /**
     *
     */
    public static final Map<String, Long> times = new LinkedHashMap<>();

    static {
        times.put("year", TimeUnit.DAYS.toMillis(365));
        times.put("month", TimeUnit.DAYS.toMillis(30));
        times.put("week", TimeUnit.DAYS.toMillis(7));
        times.put("day", TimeUnit.DAYS.toMillis(1));
        times.put("hour", TimeUnit.HOURS.toMillis(1));
        times.put("minute", TimeUnit.MINUTES.toMillis(1));
        times.put("second", TimeUnit.SECONDS.toMillis(1));
    }

    /**
     *
     * @param duration
     * @param maxLevel
     * @return
     */
    public static String toRelative(long duration, int maxLevel) {
        StringBuilder res = new StringBuilder();
        int level = 0;
        for (Map.Entry<String, Long> time : times.entrySet()) {
            long timeDelta = duration / time.getValue();
            if (timeDelta > 0) {
                res.append(timeDelta)
                        .append(" ")
                        .append(time.getKey())
                        .append(timeDelta > 1 ? "s" : "")
                        .append(", ");
                duration -= time.getValue() * timeDelta;
                level++;
            }
            if (level == maxLevel) {
                break;
            }
        }
        if ("".equals(res.toString())) {
            return "0 seconds ago";
        } else {
            res.setLength(res.length() - 2);
            res.append("");
            return res.toString();
        }
    }

    /**
     *
     * @param duration
     * @param maxLevel
     * @return
     */
    public static String toRelativeAgo(long duration, int maxLevel) {
        StringBuilder res = new StringBuilder();
        int level = 0;
        for (Map.Entry<String, Long> time : times.entrySet()) {
            long timeDelta = duration / time.getValue();
            if (timeDelta > 0) {
                res.append(timeDelta)
                        .append(" ")
                        .append(time.getKey())
                        .append(timeDelta > 1 ? "s" : "")
                        .append(", ");
                duration -= time.getValue() * timeDelta;
                level++;
            }
            if (level == maxLevel) {
                break;
            }
        }
        if ("".equals(res.toString())) {
            return "0 seconds ago";
        } else {
            res.setLength(res.length() - 2);
            res.append(" ago");
            return res.toString();
        }
    }

    /**
     *
     * @param duration
     * @return
     */
    public static String toRelative(long duration) {
        return toRelative(duration, times.size());
    }

    /**
     *
     * @param duration
     * @return
     */
    public static String toRelativeAgo(long duration) {
        return toRelativeAgo(duration, times.size());
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public static String toRelative(Date start, Date end) {
        assert start.after(end);
        return toRelative(end.getTime() - start.getTime());
    }

    /**
     *
     * @param start
     * @param end
     * @param level
     * @return
     */
    public static String toRelative(Date start, Date end, int level) {
        assert start.after(end);
        return toRelative(end.getTime() - start.getTime(), level);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("test " + toRelative(39000));
    }
}
