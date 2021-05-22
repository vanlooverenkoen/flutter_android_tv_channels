import 'package:android_tv_channels/src/model/bridge/aspect_ratio.dart';

class MovieData {
  final String title;
  final int? durationMillis;
  final String intentUri;
  final String? description;
  final DateTime? releaseDate;
  final String? genre;
  final String? posterUri;
  final PosterAspectRatio? posterAspectRatio;

  const MovieData({
    required this.title,
    required this.intentUri,
    this.durationMillis,
    this.description,
    this.releaseDate,
    this.genre,
    this.posterUri,
    this.posterAspectRatio,
  });
}
