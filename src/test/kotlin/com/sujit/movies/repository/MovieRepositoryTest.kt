package com.sujit.movies.repository

import com.sujit.movies.entity.MovieEntity
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.context.annotation.PropertySource

@DataR2dbcTest
@PropertySource("classpath:application-test.yaml")
class MovieRepositoryTest @Autowired constructor(private val movieRepository: MovieRepository) {

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
    fun `should return all movies`(): Unit = runBlocking {
        val actualMovies = movieRepository.getAll()
        Assertions.assertThat(actualMovies).isEqualTo(movies)
    }


}
