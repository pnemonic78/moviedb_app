package com.tikalk.tmdb.model

import com.tikalk.tmdb.json.model.MediaType
import com.tikalk.tmdb.json.model.Movie
import com.tikalk.tmdb.json.model.MoviesPageResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class SerialTest {
    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun deserialize_now_playing() {
        val res = javaClass.getResourceAsStream("/now_playing.json")
        assertNotNull(res!!)
        val json = Json { ignoreUnknownKeys = true }
        val response = json.decodeFromStream<MoviesPageResponse>(res)
        res.close()

        assertNotNull(response)
        assertEquals(1, response.page)
        assertEquals(239, response.totalPages)
        assertEquals(4772, response.totalResults)
        val dates = response.dates
        assertNotNull(dates!!)
        assertNotNull(dates.minimum)
        assertNotNull(dates.maximum)
        val results = response.results
        assertNotNull(results)
        assertEquals(20, results.size)
        val movie: Movie = results[0]
        assertNotNull(movie)
        assertEquals(MediaType.movie, movie.mediaType)
        assertEquals(false, movie.adult)
        assertEquals("/fqv8v6AycXKsivp1T5yKtLbGXce.jpg", movie.backdropPath)
        assertEquals(653346L, movie.id)
        assertEquals("en", movie.originalLanguage)
        assertEquals("Kingdom of the Planet of the Apes", movie.originalTitle)
        assertEquals(
            "Several generations in the future following Caesar's reign, apes are now the dominant species and live harmoniously while humans have been reduced to living in the shadows. As a new tyrannical ape leader builds his empire, one young ape undertakes a harrowing journey that will cause him to question all that he has known about the past and to make choices that will define a future for apes and humans alike.",
            movie.overview
        )
        assertEquals(10276.252, movie.popularity, 1e-3)
        assertEquals("/gKkl37BQuKTanygYQG1pyYgLVgf.jpg", movie.posterPath)
        assertEquals("Kingdom of the Planet of the Apes", movie.title)
        assertEquals(false, movie.video)
        assertEquals(6.955, movie.voteAverage, 1e-3)
        assertEquals(751, movie.voteCount)
    }
}