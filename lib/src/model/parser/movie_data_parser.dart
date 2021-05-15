import 'package:android_tv_channels/src/model/bridge/movie_data.dart';

class MovieDataParser {
  MovieDataParser._();

  static List<Map<String, dynamic>> parseMovies(List<MovieData> movies) {
    return movies
        .map((movie) => {
              'title': movie.title,
              'durationMillis': movie.durationMillis,
              'intentUri': movie.intentUri,
              'releaseDate': movie.releaseDate,
              'genre': movie.genre,
              'description': movie.description,
              'posterUri': movie.posterUri,
            })
        .toList();
  }
}
