package me.jy.config.loki;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LokiAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private static final Logger logger = LoggerFactory.getLogger(LokiAppender.class);

    private static final String LOKI_PUSH_URI = "/loki/api/v1/push";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private final LokiProperties lokiProperties;

    private final RestTemplate restTemplate;

    public LokiAppender(LokiProperties lokiProperties, RestTemplate restTemplate) {
        this.lokiProperties = lokiProperties;
        this.restTemplate = restTemplate;
    }


    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        LokiStreamWrapper streamWrapper = buildLogPush(iLoggingEvent);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.ACCEPT_CHARSET, "utf-8");
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(new ObjectMapper().writeValueAsString(streamWrapper), headers);
            restTemplate.exchange(lokiProperties.getHost() + LOKI_PUSH_URI, HttpMethod.POST, requestEntity, String.class);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }

    private LokiStreamWrapper buildLogPush(ILoggingEvent iLoggingEvent) {

        LokiStreamWrapper streamWrapper = new LokiStreamWrapper();

        LokiStream stream = new LokiStream();
        stream.getStream().putAll(lokiProperties.getLabels());
        stream.getStream().putAll(iLoggingEvent.getMDCPropertyMap());

        String[] values = new String[2];
        values[0] = String.valueOf(System.currentTimeMillis() * 1000000);
        values[1] = String.join(" ", LocalDateTime.now().format(DATE_TIME_FORMATTER), iLoggingEvent.getLevel().toString(), iLoggingEvent.getLoggerName(), iLoggingEvent.getThreadName(), iLoggingEvent.getMessage());
        if (iLoggingEvent.getLevel() == Level.ERROR) {
            values[1] += formatStackTrace(iLoggingEvent.getThrowableProxy());
        }
        stream.getValues().add(values);

        streamWrapper.addStream(stream);
        return streamWrapper;
    }

    private String formatStackTrace(IThrowableProxy iThrowableProxy) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < iThrowableProxy.getStackTraceElementProxyArray().length; i++) {
            builder.append("\n\t\t").append(iThrowableProxy.getStackTraceElementProxyArray()[i].toString());
        }
        return builder.toString();
    }

}
