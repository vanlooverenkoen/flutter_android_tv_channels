class MovieData {
  final String title;
  final int? durationMillis;
  final String intentUri;
  final String? description;
  final DateTime? releaseDate;
  final String? genre;
  final String? posterUri;

  const MovieData({
    required this.title,
    required this.intentUri,
    this.durationMillis,
    this.description,
    this.releaseDate,
    this.genre,
    this.posterUri,
  });
}
