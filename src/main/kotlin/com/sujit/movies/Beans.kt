package com.sujit.movies

import com.sujit.movies.handler.MovieHandler
import com.sujit.movies.repository.MovieRepository
import com.sujit.movies.service.MovieService
import org.springframework.context.support.beans

object Beans {

    fun beans() = beans {
        bean {
            bean<Routes>()
            bean<MovieHandler>()
            bean<MovieService>()
            bean<MovieRepository>()
            ref<Routes>().router()
        }
    }
}
