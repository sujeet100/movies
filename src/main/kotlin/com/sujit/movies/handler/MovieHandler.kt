package com.sujit.movies.handler

import com.sujit.movies.service.MovieService
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

class MovieHandler(private val movieService: MovieService) {

    suspend fun getMovies(): ServerResponse {
        return ok().json().bodyValueAndAwait(movieService.getAll())
    }
}
