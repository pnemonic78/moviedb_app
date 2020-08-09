import 'package:flutter/foundation.dart';

import 'dates.dart';
import 'media.dart';
import 'media_type.dart';

class Movie extends Media {
  String backdropPath;
  List<int> genreIds;
  List<String> originCountry;
  String originalLanguage;
  String originalTitle;
  String overview;
  String posterPath;
  DateTime releaseDate;
  String title;
  bool video;
  double voteAverage;
  int voteCount;

  Movie({
    this.backdropPath,
    this.genreIds,
    @required Media media,
    this.originCountry,
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

    list = json['origin_country'] as List;
    List<String> countries = list?.map((i) => i.toString())?.toList();

    return Movie(
      backdropPath: json['backdrop_path'],
      genreIds: genreIds,
      media: Media.fromJson(json),
      originCountry: countries,
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
