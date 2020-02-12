package me.jy._5_prototype;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {
        WeeklyReport weeklyReport = new WeeklyReport();
        System.out.println(weeklyReport == weeklyReport.clone());
    }
}
