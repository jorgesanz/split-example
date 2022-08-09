package com.example.split.configuration;

import io.split.client.SplitClient;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("split.api-token")
public class SplitConfiguration {

    @Bean
    public SplitFactory getSplitFactory(@Value("${split.api-token}") String apiToken) throws Exception {

        SplitClientConfig config = SplitClientConfig.builder()
                .setBlockUntilReadyTimeout(10000)
                .build();

        SplitFactory splitFactory = SplitFactoryBuilder.build(apiToken, config);
        return splitFactory;

    }

    @Bean
    public SplitClient getSplitClient(SplitFactory splitFactory) throws Exception {
        SplitClient client = splitFactory.client();
        client.blockUntilReady();
        return client;
    }

}
