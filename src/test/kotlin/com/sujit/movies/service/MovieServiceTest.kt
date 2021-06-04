package com.sujit.movies.service

import com.sujit.movies.entity.MovieEntity
import com.sujit.movies.entity.toMovie
import com.sujit.movies.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieServiceTest {
    private val movieRepository: MovieRepository = mockk()
    private val movieService: MovieService = MovieService(movieRepository)

    @Test
    fun `it should return all movies`(): Unit = runBlocking {
        val movies = listOf(
            MovieEntity(1, "Batman", 2020, "http://foo.com"),
            MovieEntity(2, "Batman2", 20201, "http://foo2.com")
        )

        coEvery { movieRepository.getAll() }.returns(movies)

        val actual = movieService.getAll()
        assertThat(1+2).isEqualTo(4)
        assertThat(actual).isEqualTo(movies.map { it.toMovie() })
    }
}
