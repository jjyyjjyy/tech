package me.jy.sensor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Inclusive start&end indexes
 *
 * @author jy
 */
@Data
@AllArgsConstructor
public class Range {

    private int start;
    private int end;
}
