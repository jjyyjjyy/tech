package me.jy.lang.thread.disruptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEvent {

    private String value;

}
