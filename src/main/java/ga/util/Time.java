package ga.util;

import java.util.Date;

public class Time {
    public static long getCurrentTime() {
        return new Date().getTime();
    }

    public static String toTime(long time) {
        long totalSec = time / 1000;

        long hours = totalSec / 3600;
        long min = (totalSec - hours * 3600) / 60;
        long sec = totalSec - hours * 3600 - min * 60;
        long ms = time % 1000;

        return String.format("%2dh %2dmin %2dsec %3dms", hours, min, sec, ms);
    }
}
