import 'movie.dart';

class MovieDetails extends Movie {
  MovieDetails(
      {id,
      adult,
      backdropPath,
      genreIds,
      originalLanguage,
      originalTitle,
      overview,
      popularity,
      posterPath,
      releaseDate,
      title,
      video,
      voteAverage,
      voteCount})
      : super(
            id: id,
            adult: adult,
            backdropPath: backdropPath,
            genreIds: genreIds,
            originalLanguage: originalLanguage,
            originalTitle: originalTitle,
            overview: overview,
            popularity: popularity,
            posterPath: posterPath,
            releaseDate: releaseDate,
            title: title,
            video: video,
            voteAverage: voteAverage,
            voteCount: voteCount);
}
