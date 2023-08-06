import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/date_converter.dart';

import 'media.dart';
import 'media_type.dart';

part 'movie.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
@MovieDateTimeConverter()
class Movie extends Media {
  @JsonKey(name: 'backdrop_path')
  String? backdropPath;
  @JsonKey(name: 'genre_ids')
  List<int> genreIds;
  @JsonKey(name: 'origin_country')
  List<String> originCountry;
  @JsonKey(name: 'original_language')
  String? originalLanguage;
  @JsonKey(name: 'original_title')
  String? originalTitle;
  @JsonKey(name: 'overview')
  String? overview;
  @JsonKey(name: 'poster_path')
  String? posterPath;
  @JsonKey(name: 'release_date')
  DateTime? releaseDate;
  @JsonKey(name: 'title')
  String title;
  @JsonKey(name: 'video')
  bool video;
  @JsonKey(name: 'vote_average')
  double voteAverage;
  @JsonKey(name: 'vote_count')
  int voteCount;

  Movie({
    required Media media,
    this.backdropPath,
    this.genreIds = const [],
    this.originCountry = const [],
    this.originalLanguage,
    this.originalTitle,
    this.overview,
    this.posterPath,
    this.releaseDate,
    required this.title,
    this.video = false,
    this.voteAverage = 0,
    this.voteCount = 0,
  }) : super(
          adult: media.adult,
          id: media.id,
          mediaType: media.mediaType ?? MediaType.movie,
          popularity: media.popularity,
        );

  /// Creates a [Movie] from a JSON object.
  static Movie? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$MovieFromJson(json);

  Movie.of(Media media) : this(media: media, title: media.getTitle() ?? "");

  @override
  DateTime? date() {
    return releaseDate;
  }

  @override
  String? getTitle() {
    return title;
  }
}
