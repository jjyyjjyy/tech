package me.jy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.http.HttpResponseParser;
import org.springframework.cloud.sleuth.instrument.web.HttpServerResponseParser;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletResponse;

/**
 * @author jy
 */
@SpringBootApplication
public class SpringCloudSleuthDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthDemoApplication.class, args);
    }

    @Bean(name = HttpServerResponseParser.NAME)
    public HttpResponseParser myHttpResponseParser() {
        return (response, context, span) -> {
            Object unwrap = response.unwrap();
            if (unwrap instanceof HttpServletResponse) {
                HttpServletResponse resp = (HttpServletResponse) unwrap;
                resp.addHeader("traceId", context.traceId());
                resp.addHeader("spanId", context.spanId());
            }
        };
    }
}
