import 'dart:core';

import 'movie.dart';

class Genre {
  final int id;
  final String name;

  Genre({this.id, this.name});
}

class ProductionCompany {
  final int id;
  final String name;
  final String logoPath;
  final String originCountry;

  ProductionCompany({this.id, this.name, this.logoPath, this.originCountry});
}

class ProductionCountry {
  //"iso_3166_1"
  final String id;
  final String name;

  ProductionCountry({this.id, this.name});
}

class SpokenLanguage {
  //"iso_639_1"
  final String id;
  final String name;

  SpokenLanguage({this.id, this.name});
}

enum Status {
  Rumored, //("Rumored"),
  Planned, //("Planned"),
  InProduction, //("In Production"),
  PostProduction, //("Post Production"),
  Released, //("Released"),
  Canceled //("Canceled")
}

class MovieDetails extends Movie {
  final int budget;
  final List<Genre> genres;
  final String homepage;
  final String imdbId;
  final List<ProductionCompany> productionCompanies;
  final List<ProductionCountry> productionCountries;
  final int revenue;
  final int runtime; // integer or null
  final List<SpokenLanguage> spokenLanguages;
  final Status status;
  final String tagline;

  MovieDetails(
      {id,
      adult,
      backdropPath,
      this.budget,
      this.genres,
      this.homepage,
      this.imdbId,
      originalLanguage,
      originalTitle,
      overview,
      popularity,
      posterPath,
      this.productionCompanies,
      this.productionCountries,
      releaseDate,
      this.revenue,
      this.runtime,
      this.spokenLanguages,
      this.status = Status.Released,
      this.tagline,
      title,
      video,
      voteAverage,
      voteCount})
      : super(
            id: id,
            adult: adult,
            backdropPath: backdropPath,
            genreIds:
                (genres != null) ? genres.map((g) => g.id).toList() : null,
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

  MovieDetails.of(Movie movie)
      : this(
            id: movie.id,
            adult: movie.adult,
            backdropPath: movie.backdropPath,
            genres: null,
            originalLanguage: movie.originalLanguage,
            originalTitle: movie.originalTitle,
            overview: movie.overview,
            popularity: movie.popularity,
            posterPath: movie.posterPath,
            releaseDate: movie.releaseDate,
            title: movie.title,
            video: movie.video,
            voteAverage: movie.voteAverage,
            voteCount: movie.voteCount);
}
