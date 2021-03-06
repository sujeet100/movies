package com.sujit.movies

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfig(private val flywayProperties: FlywayProperties) {

    @Bean(initMethod = "migrate")
    fun flyway(): Flyway? {
        return Flyway(
            Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(flywayProperties.url, flywayProperties.user, flywayProperties.password)
        )
    }
}
