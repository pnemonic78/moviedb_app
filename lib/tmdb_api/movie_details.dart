import 'dart:core';

import 'movie.dart';

class Genre {
  final int id;
  final String name;

  Genre({this.id, this.name});

  static List<int> toIds(List<Genre> genres) {
    return (genres != null) ? genres.map((g) => g.id).toList() : null;
  }

  factory Genre.toJson(Map<String, dynamic> json) {
    return Genre(id: json['id'], name: json['name']);
  }
}

class ProductionCompany {
  final int id;
  final String name;
  final String logoPath;
  final String originCountry;

  ProductionCompany({this.id, this.name, this.logoPath, this.originCountry});

  factory ProductionCompany.toJson(Map<String, dynamic> json) {
    return ProductionCompany(
        id: json['id'],
        name: json['name'],
        logoPath: json['logo_path'],
        originCountry: json['origin_country']);
  }
}

class ProductionCountry {
  final String id;
  final String name;

  ProductionCountry({this.id, this.name});

  factory ProductionCountry.toJson(Map<String, dynamic> json) {
    return ProductionCountry(id: json['iso_3166_1'], name: json['name']);
  }
}

class SpokenLanguage {
  final String id;
  final String name;

  SpokenLanguage({this.id, this.name});

  factory SpokenLanguage.toJson(Map<String, dynamic> json) {
    return SpokenLanguage(id: json['iso_639_1'], name: json['name']);
  }
}

class Status {
  final String _value;

  static const rumored = Status("Rumored");
  static const planned = Status("Planned");
  static const inProduction = Status("In Production");
  static const postProduction = Status("Post Production");
  static const released = Status("Released");
  static const canceled = Status("Canceled");

  static const values = [
    rumored,
    planned,
    inProduction,
    postProduction,
    released,
    canceled
  ];

  const Status(this._value);

  factory Status.fromJson(String json) {
    return values.firstWhere((v) => json == v._value);
  }
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
      this.status = Status.released,
      this.tagline,
      title,
      video,
      voteAverage,
      voteCount})
      : super(
            id: id,
            adult: adult,
            backdropPath: backdropPath,
            genreIds: Genre.toIds(genres),
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

  /// Creates a [MovieDetails] from a JSON object.
  factory MovieDetails.fromJson(Map<String, dynamic> json) {
    var list = json['genres'] as List;
    List<Genre> genres = list.map((j) => Genre.toJson(j)).toList();

    list = json['production_companies'] as List;
    List<ProductionCompany> productionCompanies =
        list.map((j) => ProductionCompany.toJson(j)).toList();

    list = json['production_countries'] as List;
    List<ProductionCountry> productionCountries =
        list.map((j) => ProductionCountry.toJson(j)).toList();

    list = json['spoken_languages'] as List;
    List<SpokenLanguage> spokenLanguages =
        list.map((j) => SpokenLanguage.toJson(j)).toList();

    return MovieDetails(
      adult: json['adult'],
      backdropPath: json['backdrop_path'],
      id: json['id'],
      originalLanguage: json['original_language'],
      originalTitle: json['original_title'],
      overview: json['overview'],
      popularity: json['popularity'].toDouble(),
      posterPath: json['poster_path'],
      releaseDate: DateTime.parse(json['release_date']),
      title: json['title'],
      video: json['video'],
      voteAverage: json['vote_average'].toDouble(),
      voteCount: json['vote_count'],
      budget: json['budget'],
      genres: genres,
      homepage: json['homepage'],
      imdbId: json['imdb_id'],
      productionCompanies: productionCompanies,
      productionCountries: productionCountries,
      revenue: json['revenue'],
      runtime: json['runtime'],
      spokenLanguages: spokenLanguages,
      status: Status.fromJson(json['status']),
      tagline: json['tagline'],
    );
  }
}
