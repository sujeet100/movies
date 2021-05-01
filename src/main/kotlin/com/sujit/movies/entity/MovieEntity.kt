package com.sujit.movies.entity

import com.sujit.movies.domain.Movie
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("movies")
data class MovieEntity(
    val id: Int,
    val name: String,
    val year: Int,
    @Column("imageUrl")

    val imageUrl: String
)

fun MovieEntity.toMovie(): Movie {
    return Movie(this.name, this.year, this.imageUrl, 0)
}
