package me.jy._8_bridge;

/**
 * @author jy
 */
public abstract class ReportPrinter {

    protected DataReader dataReader;

    public void setDataReader(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    public abstract void print();
}
