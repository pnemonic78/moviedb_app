import 'package:flutter/foundation.dart';

import 'dates.dart';
import 'movie.dart';
import 'person.dart';

class MovieCredit extends Movie {
  final String creditId;
  final String backdropPath;
  final int episodeCount;
  final DateTime firstAirDate;
  final List<int> genreIds;
  final Movie movie;
  final List<String> originCountry;
  final String originalLanguage;
  final String originalName;
  final String originalTitle;
  final String overview;
  final Person person;
  final String posterPath;
  final DateTime releaseDate;
  final String title;
  final bool video;
  final double voteAverage;
  final int voteCount;

  MovieCredit({
    this.backdropPath,
    @required this.creditId,
    this.episodeCount,
    this.firstAirDate,
    this.genreIds,
    @required this.movie,
    this.originCountry,
    this.originalLanguage,
    this.originalName,
    this.originalTitle,
    this.overview,
    @required this.person,
    this.posterPath,
    this.releaseDate,
    this.title,
    this.video,
    this.voteAverage,
    this.voteCount,
  })  : assert(creditId != null),
        super(
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

  /// Creates a [MovieCredit] from a JSON object.
  factory MovieCredit.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['genre_ids'] as List;
    List<int> genreIds = list?.map((i) => i as int)?.toList();

    list = json['origin_country'] as List;
    List<String> countries = list?.map((i) => i.toString())?.toList();

    return MovieCredit(
      backdropPath: json['backdrop_path'],
      creditId: json['credit_id'],
      episodeCount: json['episode_count'],
      firstAirDate: parseDateTime(json['first_air_date']),
      genreIds: genreIds,
      movie: Movie.fromJson(json),
      originCountry: countries,
      originalLanguage: json['original_language'],
      originalName: json['original_name'],
      originalTitle: json['original_title'],
      overview: json['overview'],
      person: Person.fromJson(json),
      posterPath: json['poster_path'],
      releaseDate: parseDateTime(json['release_date']),
      title: json['title'],
      video: json['video'],
      voteAverage: json['vote_average']?.toDouble(),
      voteCount: json['vote_count'],
    );
  }
}
