import 'dart:ui';

import 'package:json_annotation/json_annotation.dart';

class MovieLocaleConverter implements JsonConverter<Locale, String> {
  const MovieLocaleConverter();

  @override
  Locale fromJson(String json) {
    if ((json == null) || json.isEmpty) return null;
    return Locale(json);
  }

  static Locale fromJsons(Map<String, dynamic> json) {
    if ((json == null) || json.isEmpty) return null;
    final String languageCode = json['iso_639_1'] ?? 'und';
    final String countryCode = json['iso_3166_1'] ?? '';
    return Locale(languageCode, countryCode);
  }

  @override
  String toJson(Locale object) => object.toString();
}
