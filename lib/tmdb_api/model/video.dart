import 'package:flutter/material.dart';

class VideoType {
  final String _value;

  static const trailer = VideoType("Trailer");
  static const teaser = VideoType("Teaser");
  static const clip = VideoType("Clip");
  static const featurette = VideoType("Featurette");
  static const behindTheScenes = VideoType("Behind the Scenes");
  static const bloopers = VideoType("Bloopers");

  static const values = [
    trailer,
    teaser,
    clip,
    featurette,
    behindTheScenes,
    bloopers
  ];

  const VideoType(this._value);

  @override
  String toString() {
    return _value;
  }

  factory VideoType.fromJson(String json) {
    return values.firstWhere((v) => json == v._value);
  }
}

class MovieVideo {
  String id;
  Locale locale;
  String key;
  String name;
  String site;
  int size;
  VideoType type;

  MovieVideo({
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

  /// Creates a [MovieVideo] from a JSON object.
  factory MovieVideo.fromJson(Map<String, dynamic> json) {
    String languageCode = json['iso_639_1'] ?? 'und';
    String countryCode = json['iso_3166_1'] ?? '';
    Locale locale = Locale(languageCode, countryCode);

    return MovieVideo(
      id: json['id'],
      locale: locale,
      key: json['key'],
      name: json['name'],
      site: json['site'],
      size: json['size'],
      type: VideoType.fromJson(json['type']),
    );
  }
}
