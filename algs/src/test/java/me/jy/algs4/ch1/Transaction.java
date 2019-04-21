package me.jy.algs4.ch1;

import java.math.BigDecimal;

/**
 * @author jy
 */
public class Transaction {

    private final String from;

    private final SmartDate date;

    private final BigDecimal amount;

    public Transaction(String from, SmartDate date, BigDecimal amount) {
        this.from = from;
        this.date = date;
        this.amount = amount;
    }

    public Transaction(String text) {
        String[] strings = text.split("\\s");
        this.from = strings[0];
        this.amount = new BigDecimal(strings[2]);
        this.date = new SmartDate(strings[1]);

    }

    public String getFrom() {
        return from;
    }

    public SmartDate getSmartDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    // tag::ex1.2.14[]
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Transaction)) {
            return false;
        }
        Transaction another = (Transaction) obj;
        return another.getAmount().equals(this.getAmount())
            && another.getFrom().equals(this.getFrom())
            && another.getSmartDate().equals(this.getSmartDate());
    }
    // end::ex1.2.14[]
}
