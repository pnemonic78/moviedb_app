class Movie {
  final int id;
  final bool adult;
  final String backdropPath; //nullable
  final List<int> genreIds;
  final String originalLanguage;
  final String originalTitle;
  final String overview; //nullable
  final double popularity;
  final String posterPath; //nullable
  final DateTime releaseDate;
  final String title;
  final bool video;
  final double voteAverage;
  final int voteCount;

  Movie(
      {this.id,
      this.adult,
      this.backdropPath,
      this.genreIds,
      this.originalLanguage,
      this.originalTitle,
      this.overview,
      this.popularity,
      this.posterPath,
      this.releaseDate,
      this.title,
      this.video,
      this.voteAverage,
      this.voteCount});
}
