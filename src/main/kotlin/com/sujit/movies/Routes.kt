package com.sujit.movies

import com.sujit.movies.handler.MovieHandler
import org.springframework.web.reactive.function.server.coRouter

class Routes(private val movieHandler: MovieHandler) {

    fun router() = coRouter {
        GET("/movies", movieHandler::getMovies)
    }

}
