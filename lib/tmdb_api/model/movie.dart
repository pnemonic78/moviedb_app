import 'package:flutter/foundation.dart';

import 'dates.dart';
import 'media.dart';
import 'media_type.dart';

class Movie extends Media {
  final String backdropPath;
  final List<int> genreIds;
  final Media media;
  final String originalLanguage;
  final String originalTitle;
  final String overview;
  final String posterPath;
  final DateTime releaseDate;
  final String title;
  final bool video;
  final double voteAverage;
  final int voteCount;

  Movie({
    this.backdropPath,
    this.genreIds,
    @required this.media,
    this.originalLanguage,
    @required this.originalTitle,
    this.overview,
    this.posterPath,
    this.releaseDate,
    @required this.title,
    this.video,
    this.voteAverage,
    this.voteCount,
  })  : assert(media != null),
        super(
          adult: media.adult,
          id: media.id,
          mediaType: media.mediaType ?? MediaType.movie,
          popularity: media.popularity,
        );

  /// Creates a [Movie] from a JSON object.
  factory Movie.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    var list = json['genre_ids'] as List;
    List<int> genreIds = list?.map((i) => i as int)?.toList();

    return Movie(
      backdropPath: json['backdrop_path'],
      genreIds: genreIds,
      media: Media.fromJson(json),
      originalLanguage: json['original_language'],
      originalTitle: json['original_title'],
      overview: json['overview'],
      posterPath: json['poster_path'],
      releaseDate: parseDateTime(json['release_date']),
      title: json['title'],
      video: json['video'],
      voteAverage: json['vote_average']?.toDouble(),
      voteCount: json['vote_count'],
    );
  }

  Movie.of(Media media) : this(media: media);

  @override
  DateTime date() {
    return releaseDate;
  }

  @override
  String getTitle() {
    return title ?? originalTitle;
  }
}
