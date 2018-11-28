package me.jy._5_prototype;

import lombok.Data;

/**
 * @author jy
 */
@Data
public class WeeklyReport implements Cloneable {

    private String name;

    private String title;

    private String content;

    public WeeklyReport clone() {
        try {
            return (WeeklyReport) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
