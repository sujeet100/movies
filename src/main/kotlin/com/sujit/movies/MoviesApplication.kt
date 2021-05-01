package com.sujit.movies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FlywayProperties::class)
class MoviesApplication

fun main(args: Array<String>) {
    runApplication<MoviesApplication>(*args)
}
