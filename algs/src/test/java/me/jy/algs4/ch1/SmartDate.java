package me.jy.algs4.ch1;

import java.time.Year;
import java.util.stream.IntStream;

/**
 * @author jy
 */
public class SmartDate {

    private static final int[] MONTH_30_DAYS = new int[]{4, 6, 9, 11};

    private final int year;

    private final int month;

    private final int day;

    public SmartDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        check();
    }

    public SmartDate(String text) {
        String[] strings = text.split("/");
        this.year = Integer.valueOf(strings[0]);
        this.month = Integer.valueOf(strings[1]);
        this.day = Integer.valueOf(strings[2]);
    }

    private void check() {
        // check the range
        if (month > 12 || month < 1) {
            throw new IllegalArgumentException("Illegal month: " + month);
        }
        if (day > 31 || day < 1) {
            throw new IllegalArgumentException("Illegal day: " + day);
        }
        // check the 31st day
        if (BinarySearch.search(MONTH_30_DAYS, month) > 0 && day == 31) {
            throw new IllegalArgumentException("Illegal day: " + day);
        }
        // check if leap
        if (month == 2 && day > 28 && !isLeap(year)) {
            throw new IllegalArgumentException("Illegal day: " + day);
        }

    }

    // tag::ex1.2.12[]
    public Week dayOfWeek() {
        // 1999/12/31=FRIDAY
        int daysOfPastYear = 365 * (year - 2000) + (year == 2000 ? 0 : (int) IntStream.rangeClosed(2000, year).filter(this::isLeap).count());
        int daysPast = daysOfPastYear + computeDaysOfThisYear();
        return Week.of((daysPast + 4) % 7);
    }

    private int computeDaysOfThisYear() {
        int result = 0;
        for (int i = 1; i < month; i++) {
            if (BinarySearch.search(MONTH_30_DAYS, i) > 0) {
                result += 30;
            } else result += 31;
        }

        if (month > 2) {
            if (Year.isLeap(year)) {
                result -= 2;
            } else result -= 3;
        }
        return result + day;
    }

    private boolean isLeap(int y) {
        return (y & 3) == 0 && (y % 100) != 0 || y % 400 == 0;
    }

    public enum Week {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        static Week of(int index) {
            return Week.class.getEnumConstants()[index];
        }
    }
    // end::ex1.2.12[]
}
