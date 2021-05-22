package be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.model

import java.text.DateFormat
import java.util.Date

data class MovieData(
    val title: String,
    val intentUri: String,
    val durationMillis: Int?,
    val description: String?,
    val releaseDate: Date?,
    val genre: String?,
    val posterUri: String?,
    val posterAspectRatio: String?
)

object MovieDataParser {
    fun parseMovies(data: List<Map<String, Any>>): List<MovieData> = data.map {
        val date = it["releaseDate"] as String?
        MovieData(
            title = it["title"] as String,
            durationMillis = it["durationMillis"] as Int?,
            intentUri = it["intentUri"] as String,
            releaseDate = if (date == null) null else DateFormat.getInstance().parse(date),
            genre = it["genre"] as String?,
            description = it["description"] as String?,
            posterUri = it["posterUri"] as String?,
            posterAspectRatio = it["posterAspectRatio"] as String?
        )
    }
}