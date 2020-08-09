import 'package:flutter/material.dart';

import 'media_image_type.dart';

class MovieImage {
  String id;
  Locale locale;
  String key;
  String name;
  String site;
  int size;
  MovieImageType type;

  MovieImage({
    this.id,
    this.locale,
    this.key,
    this.name,
    this.site,
    this.size,
    this.type,
  });

  @override
  String toString() {
    return '{id: $id, name: "$name", type: $type}';
  }

  /// Creates a [MovieImage] from a JSON object.
  factory MovieImage.fromJson(Map<String, dynamic> json) {
    final String languageCode = json['iso_639_1'] ?? 'und';
    final String countryCode = json['iso_3166_1'];
    final Locale locale = Locale(languageCode, countryCode);

    return MovieImage(
      id: json['id'],
      locale: locale,
      key: json['key'],
      name: json['name'],
      site: json['site'],
      size: json['size'],
      type: MovieImageType.valueOf(json['type']),
    );
  }
}
