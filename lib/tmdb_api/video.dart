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

class Video {
  final String id;
  final Locale locale;
  final String key;
  final String name;
  final String site;
  final int size;
  final VideoType type;

  const Video(
      {this.id,
      this.locale,
      this.key,
      this.name,
      this.site,
      this.size,
      this.type});


  @override
  String toString() {
    return '{id: $id, name: "$name", type: $type}';
  }

  /// Creates a [Video] from a JSON object.
  factory Video.fromJson(Map<String, dynamic> json) {
    final String languageCode = json['iso_639_1'] ?? 'und';
    final String countryCode = json['iso_3166_1'];
    final Locale locale = Locale(languageCode, countryCode);

    return Video(
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
