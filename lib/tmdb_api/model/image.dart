import 'package:flutter/material.dart';

class MovieImage {
  String id;
  Locale locale;
  int width;
  int height;
  double aspectRatio;
  String path;
  double voteAverage;
  int voteCount;

  MovieImage({
    this.id,
    this.locale,
    this.width,
    this.height,
    this.aspectRatio,
    this.path,
    this.voteAverage,
    this.voteCount,
  });

  @override
  String toString() {
    return '{id: $id, path: "$path", size: $width x $height}';
  }

  /// Creates a [MovieImage] from a JSON object.
  factory MovieImage.fromJson(Map<String, dynamic> json) {
    String languageCode = json['iso_639_1'] ?? 'und';
    String countryCode = json['iso_3166_1'] ?? '';
    Locale locale = Locale(languageCode, countryCode);

    return MovieImage(
      id: json['id'],
      locale: locale,
      width: json['width'],
      height: json['height'],
      aspectRatio: json['aspect_ratio'],
      path: json['file_path'],
      voteAverage: json['vote_average'].toDouble(),
      voteCount: json['vote_count'],
    );
  }
}
