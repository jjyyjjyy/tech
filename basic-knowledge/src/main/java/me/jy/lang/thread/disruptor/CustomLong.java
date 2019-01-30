package me.jy.lang.thread.disruptor;

import lombok.Data;

/**
 * @author jy
 */
@Data
public class CustomLong extends RhsPadding {

    private volatile long value;

}
