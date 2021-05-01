package com.sujit.movies.service

import com.sujit.movies.domain.Movie
import com.sujit.movies.entity.toMovie
import com.sujit.movies.repository.MovieRepository

class MovieService(private val movieRepository: MovieRepository) {

    suspend fun getAll(): List<Movie> {
        return movieRepository.getAll().map { it.toMovie() }
    }
}

