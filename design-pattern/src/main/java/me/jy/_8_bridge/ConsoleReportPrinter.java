package me.jy._8_bridge;

/**
 * @author jy
 */
public class ConsoleReportPrinter extends ReportPrinter {

    @Override
    public void print() {
        System.out.println(dataReader.read());
    }
}
