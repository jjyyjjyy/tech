package me.jy.config.loki;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jy
 */
@Data
@ConfigurationProperties(prefix = "loki")
public class LokiProperties {

    private String host;

    private Map<String, String> labels = new HashMap<>();
}
