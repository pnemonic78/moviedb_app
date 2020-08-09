import 'dart:core';

import 'package:tmdb/tmdb_api/credits_response.dart';

import 'genre.dart';
import 'language.dart';
import 'movie.dart';
import 'movie_status.dart';
import 'production_company.dart';
import 'production_country.dart';

class MovieDetails extends Movie {
  int budget;
  CreditsResponse credits;
  List<Genre> genres;
  String homepage;
  String imdbId;
  Movie movie;
  List<ProductionCompany> productionCompanies;
  List<ProductionCountry> productionCountries;
  int revenue;
  int runtime;
  List<SpokenLanguage> spokenLanguages;
  MovieStatus status;
  String tagline;

  MovieDetails({
    this.budget,
    this.genres,
    this.homepage,
    this.imdbId,
    this.movie,
    this.productionCompanies,
    this.productionCountries,
    this.revenue,
    this.runtime,
    this.spokenLanguages,
    this.status = MovieStatus.released,
    this.tagline,
    this.credits,
  })  :super(
          backdropPath: movie.backdropPath,
          genreIds: movie.genreIds,
          media: movie.media,
          originalLanguage: movie.originalLanguage,
          originalTitle: movie.originalTitle,
          overview: movie.overview,
          posterPath: movie.posterPath,
          releaseDate: movie.releaseDate,
          title: movie.title,
          video: movie.video,
          voteAverage: movie.voteAverage,
          voteCount: movie.voteCount,
        );

  MovieDetails.of(Movie movie) : this(movie: movie);

  /// Creates a [MovieDetails] from a JSON object.
  factory MovieDetails.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['genres'] as List;
    List<Genre> genres = list.map((i) => Genre.fromJson(i)).toList();

    list = json['production_companies'] as List;
    List<ProductionCompany> productionCompanies =
        list?.map((i) => ProductionCompany.fromJson(i))?.toList();

    list = json['production_countries'] as List;
    List<ProductionCountry> productionCountries =
        list?.map((i) => ProductionCountry.fromJson(i))?.toList();

    list = json['spoken_languages'] as List;
    List<SpokenLanguage> spokenLanguages =
        list.map((i) => SpokenLanguage.fromJson(i)).toList();

    return MovieDetails(
      budget: json['budget'],
      credits: CreditsResponse.fromJson(json['credits']),
      genres: genres,
      homepage: json['homepage'],
      imdbId: json['imdb_id'],
      movie: Movie.fromJson(json),
      productionCompanies: productionCompanies,
      productionCountries: productionCountries,
      revenue: json['revenue'],
      runtime: json['runtime'],
      spokenLanguages: spokenLanguages,
      status: MovieStatus.valueOf(json['status']),
      tagline: json['tagline'],
    );
  }
}
