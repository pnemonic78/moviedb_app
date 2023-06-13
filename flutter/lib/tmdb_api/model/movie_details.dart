import 'dart:core';

import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/credits_response.dart';

import '../date_converter.dart';
import 'genre.dart';
import 'language.dart';
import 'media_type.dart';
import 'movie.dart';
import 'production_company.dart';
import 'production_country.dart';

part 'movie_details.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieDateTimeConverter()
class MovieDetails extends Movie {
  @JsonKey(name: 'budget')
  int budget;
  @JsonKey(name: 'credits')
  CreditsResponse? credits;
  @JsonKey(name: 'genres')
  List<Genre> genres;
  @JsonKey(name: 'homepage')
  String? homepage;
  @JsonKey(name: 'imdb_id')
  String? imdbId;
  @JsonKey(name: 'production_companies')
  List<ProductionCompany> productionCompanies;
  @JsonKey(name: 'production_countries')
  List<ProductionCountry> productionCountries;
  @JsonKey(name: 'revenue')
  int revenue;
  @JsonKey(name: 'runtime')
  int runtime;
  @JsonKey(name: 'spoken_languages')
  List<SpokenLanguage> spokenLanguages;
  @JsonKey(name: 'status')
  String? status;
  @JsonKey(name: 'tagline')
  String? tagline;

  MovieDetails({
    required Movie movie,
    this.budget = 0,
    this.genres = const [],
    this.homepage,
    this.imdbId,
    this.productionCompanies = const [],
    this.productionCountries = const [],
    this.revenue = 0,
    this.runtime = 0,
    this.spokenLanguages = const [],
    this.status,
    this.tagline,
    this.credits,
  }) : super(
          backdropPath: movie.backdropPath,
          genreIds: movie.genreIds,
          media: movie,
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
  static MovieDetails? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$MovieDetailsFromJson(json);
}
