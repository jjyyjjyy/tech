package me.jy._8_bridge;

/**
 * @author jy
 */
public class Main {

    public static void main(String[] args) {
        TextDataReader textDataReader = new TextDataReader();
        ConsoleReportPrinter printer = new ConsoleReportPrinter();
        printer.setDataReader(textDataReader);

        printer.print();
    }
}
