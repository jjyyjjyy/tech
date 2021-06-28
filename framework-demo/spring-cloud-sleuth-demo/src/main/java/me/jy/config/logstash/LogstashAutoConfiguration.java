package me.jy.config.logstash;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSONObject;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * @author jy
 */
@Configuration
@ConditionalOnProperty("logstash.host")
@ConditionalOnClass(Logger.class)
@EnableConfigurationProperties(LogstashProperties.class)
public class LogstashAutoConfiguration implements ApplicationRunner {

    private static final String APPENDER_NAME = "LOGSTASH";

    @Autowired
    private LogstashProperties logstashProperties;

    @Autowired
    private Environment environment;

    @Autowired
    private InetUtils inetUtils;

    @Override
    public void run(ApplicationArguments args) {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        if (Objects.nonNull(loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).getAppender(APPENDER_NAME))) {
            return;
        }
        LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        appender.setContext(loggerContext);
        appender.setName(APPENDER_NAME);
        appender.addDestinations(new InetSocketAddress(logstashProperties.getHost(), logstashProperties.getPort()));

        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setVersion(environment.resolvePlaceholders(logstashProperties.getVersion()));
        JSONObject customFields = new JSONObject(4);
        customFields.put("service", environment.resolvePlaceholders(logstashProperties.getApplicationName()));
        customFields.put("env", environment.resolvePlaceholders(logstashProperties.getEnv()));
        customFields.put("ip", inetUtils.findFirstNonLoopbackHostInfo().getIpAddress());
        encoder.setCustomFields(customFields.toJSONString());
        appender.setEncoder(encoder);

        appender.start();
        loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(appender);
    }

}
