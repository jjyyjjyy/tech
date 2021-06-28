package me.jy.config.logstash;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jy
 */
@Data
@ConfigurationProperties(prefix = "logstash")
public class LogstashProperties {

    private String host;

    private int port;

    private String applicationName = "${spring.application.name:application}";

    private String version = "${spring.application.version:1.0.0}";

    private String env = "${spring.profiles.active:default}";

}
