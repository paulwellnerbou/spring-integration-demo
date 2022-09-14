package com.example.springintegrationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.http.dsl.Http;

@SpringBootApplication
@EnableIntegration
public class SpringIntegrationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationDemoApplication.class, args);
    }

    @Bean
    public IntegrationFlow inbound() {
        return IntegrationFlows.from(Http.inboundGateway("/path/{argument}")
                                .requestMapping(m -> m.methods(HttpMethod.GET))
                        // how do I consume the {argument} and add it to the payload?
                ).transform(source -> {
                    // Why is source here empty:  {LinkedMultiValueMap} size = 0
                    // How can I access the whole message, not only the payload?
                    return source;
                }).log(
                        // but log shows the real message?
                        // GenericMessage [payload={}, headers={http_requestMethod=GET, replyChannel=...,
                        //  errorChannel=..., host=localhost:41587, http_requestUrl=http://localhost:41587/path/my-argument, ...
                        // ]
                        LoggingHandler.Level.WARN
                )
                .transform(source -> "Hello world!")
                .logAndReply();
    }
}
