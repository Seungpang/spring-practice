package me.seungpang.advenced;

import me.seungpang.advenced.trace.logtrace.LogTrace;
import me.seungpang.advenced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace(); //스프링 빈으로 등록 싱글톤으로 등록된다.
    }
}
