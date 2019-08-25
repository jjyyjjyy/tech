package me.jy.sensor;

import java.util.Set;

/**
 * 敏感词加载
 *
 * @author jy
 */
public interface SensitiveWordInitializer {

    Set<String> load();

}
