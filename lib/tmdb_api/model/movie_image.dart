import 'package:flutter/material.dart';

class MovieImageType {
  final String _value;

  static const trailer = MovieImageType("Trailer");
  static const teaser = MovieImageType("Teaser");
  static const clip = MovieImageType("Clip");
  static const featurette = MovieImageType("Featurette");
  static const behindTheScenes = MovieImageType("Behind the Scenes");
  static const bloopers = MovieImageType("Bloopers");

  static const values = [
    trailer,
    teaser,
    clip,
    featurette,
    behindTheScenes,
    bloopers
  ];

  const MovieImageType(this._value);

  @override
  String toString() {
    return _value;
  }

  factory MovieImageType.fromJson(String json) {
    return values.firstWhere((v) => json == v._value);
  }
}

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
      type: MovieImageType.fromJson(json['type']),
    );
  }
}
