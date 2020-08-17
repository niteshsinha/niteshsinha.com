package com.niteshsinha.candid.service;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableR2dbcRepositories
public class CandidConfig extends AbstractR2dbcConfiguration {

    private final String candidHost;
    private final String candidDbHost;
    private final String candidDbUsername;
    private final String candidDbPassword;
    private final String candidDbName;

    public CandidConfig(
            @Value("${cynic.statusalertsite.url:myurl}") String url,
            @Value("${com.niteshsinha.candid.db.host:localhost}") String dbHost,
            @Value("${com.niteshsinha.candid.db.username:postgres}") String dbUsername,
            @Value("${com.niteshsinha.candid.db.password:postgres}") String dbPassword,
            @Value("${com.niteshsinha.candid.db.name:pod_check}") String dbName
    ) {
        this.candidDbHost = dbHost;
        this.candidDbName = dbName;
        this.candidDbPassword = dbPassword;
        this.candidDbUsername = dbUsername;
        this.candidHost = url;

    }


    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(candidDbHost)
                        .port(5432)
                        .username(candidDbUsername)
                        .password(candidDbPassword)
                        .database(candidDbName)
                        .build()
        );
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(candidHost)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(-1))
                        .build())
                .build();
    }
}
