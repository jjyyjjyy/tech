package me.jy.config.loki;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author jy
 */
@Configuration
@ConditionalOnProperty("loki.host")
@ConditionalOnClass(Logger.class)
@EnableConfigurationProperties(LokiProperties.class)
public class LokiAutoConfiguration implements ApplicationRunner {

    private static final String APPENDER_NAME = "LOKI";

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LokiProperties lokiProperties;

    @Override
    public void run(ApplicationArguments args) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        if (!Objects.isNull(loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).getAppender(APPENDER_NAME))) {
            return;
        }
        LokiAppender appender = new LokiAppender(lokiProperties, restTemplate);
        appender.setContext(loggerContext);
        appender.setName(APPENDER_NAME);
        appender.start();
        loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(appender);
    }

}
