package com.sujit.movies.handler

import com.sujit.movies.entity.MovieEntity
import com.sujit.movies.repository.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@PropertySource("classpath:application-test.yaml")
class MoviesIntegrationTest @Autowired constructor(val client: WebTestClient, val movieRepository: MovieRepository) {

    private val movies = listOf(
        MovieEntity(1, "Batman", 2020, "http://foo.com"),
        MovieEntity(2, "Batman2", 20201, "http://foo2.com")
    )

    @BeforeEach
    fun beforeEach(): Unit = runBlocking {
        movieRepository.deleteAll()
        movies.forEach { movieRepository.save(it) }
    }


    @Test
    fun `it should return all movies`() {
        client.get()
            .uri("/movies")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .json(
                """
                [
                  {
                    "name": "Batman",
                    "year": 2020,
                    "imageUrl": "http://foo.com",
                    "rating": 0
                  },
                  {
                    "name": "Batman2",
                    "year": 20201,
                    "imageUrl": "http://foo2.com",
                    "rating": 0
                  }
                ]
                """.trimIndent()
            )
    }
}
