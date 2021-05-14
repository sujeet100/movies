package com.sujit.movies.handler

import com.sujit.movies.Routes
import com.sujit.movies.domain.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

internal class MovieRouterTest {
    private val movieHandler: MovieHandler = mockk()
    private val client = WebTestClient.bindToRouterFunction(Routes(movieHandler).router()).build()

    @Test
    fun `it should return movies json`(): Unit = runBlocking {
        val movies = listOf(
            Movie("Batman", 2020, "http://foo.com", 0),
            Movie("Batman2", 20201, "http://foo2.com", 0)
        )

        coEvery {
            movieHandler.getMovies()
        }.returns(ok().bodyValueAndAwait(movies))
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
