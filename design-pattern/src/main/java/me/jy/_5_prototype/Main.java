package me.jy._5_prototype;

/**
 * @author jy
 */
public class Main {

    public static void main(String[] args) {
        WeeklyReport weeklyReport = new WeeklyReport();
        System.out.println(weeklyReport == weeklyReport.clone());
    }
}
