package com.sujit.movies.repository

import com.sujit.movies.entity.MovieEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MovieRepository : CoroutineCrudRepository<MovieEntity, Int> {

    @Query(
        """
       Select * from movies
    """
    )
    suspend fun getAll(): List<MovieEntity>
}
