package me.jy._8_bridge;

/**
 * @author jy
 */
public class SentryReporPrinter extends ReportPrinter {
    @Override
    public void print() {
        System.out.println("Send to sentry : " + dataReader.read());
    }
}
