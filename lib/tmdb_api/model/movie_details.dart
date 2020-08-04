import 'dart:core';

import 'package:tmdb/tmdb_api/credits_response.dart';

import 'dates.dart';
import 'genre.dart';
import 'language.dart';
import 'movie.dart';
import 'movie_status.dart';
import 'production_company.dart';
import 'production_country.dart';

class MovieDetails extends Movie {
  final int budget;
  final List<Genre> genres;
  final String homepage;
  final String imdbId;
  final List<ProductionCompany> productionCompanies;
  final List<ProductionCountry> productionCountries;
  final int revenue;
  final int runtime;
  final List<SpokenLanguage> spokenLanguages;
  final MovieStatus status;
  final String tagline;
  final CreditsResponse credits;

  MovieDetails(final int id,
      {final bool adult,
      final String backdropPath,
      this.budget,
      this.genres,
      this.homepage,
      this.imdbId,
      final String originalLanguage,
      final String originalTitle,
      final String overview,
      final double popularity,
      final String posterPath,
      this.productionCompanies,
      this.productionCountries,
      final DateTime releaseDate,
      this.revenue,
      this.runtime,
      this.spokenLanguages,
      this.status = MovieStatus.released,
      this.tagline,
      final String title,
      final bool video,
      final double voteAverage,
      final int voteCount,
      this.credits})
      : super(id,
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
      : this(movie.id,
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
    List<Genre> genres = list.map((i) => Genre.fromJson(i)).toList();

    list = json['production_companies'] as List;
    List<ProductionCompany> productionCompanies =
        list.map((i) => ProductionCompany.fromJson(i)).toList();

    list = json['production_countries'] as List;
    List<ProductionCountry> productionCountries =
        list.map((i) => ProductionCountry.fromJson(i)).toList();

    list = json['spoken_languages'] as List;
    List<SpokenLanguage> spokenLanguages =
        list.map((i) => SpokenLanguage.fromJson(i)).toList();

    return MovieDetails(
      json['id'],
      adult: json['adult'],
      backdropPath: json['backdrop_path'],
      originalLanguage: json['original_language'],
      originalTitle: json['original_title'],
      overview: json['overview'],
      popularity: json['popularity'].toDouble(),
      posterPath: json['poster_path'],
      releaseDate: parseDateTime(json['release_date']),
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
      status: MovieStatus.fromJson(json['status']),
      tagline: json['tagline'],
      credits: CreditsResponse.fromJson(json['credits']),
    );
  }
}
