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

    private final String alertSiteHost;
    private final String alertsiteDbHost;
    private final String alertsiteDbUsername;
    private final String alertsiteDbPassword;
    private final String alertsiteDbname;

    public CandidConfig(
            @Value("${cynic.statusalertsite.url}") String url,
            @Value("${cynic.statusalertsite.db.host:localhost}") String dbHost,
            @Value("${cynic.statusalertsite.db.username:postgres}") String dbUsername,
            @Value("${cynic.statusalertsite.db.password:postgres}") String dbPassword,
            @Value("${cynic.statusalertsite.db.name:pod_check}") String dbName
    ) {
        this.alertsiteDbHost = dbHost;
        this.alertsiteDbname = dbName;
        this.alertsiteDbPassword = dbPassword;
        this.alertsiteDbUsername = dbUsername;
        this.alertSiteHost = url;

    }


    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(alertsiteDbHost)
                        .port(5432)
                        .username(alertsiteDbUsername)
                        .password(alertsiteDbPassword)
                        .database(alertsiteDbname)
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
                .baseUrl(alertSiteHost)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(-1))
                        .build())
                .build();
    }
}
