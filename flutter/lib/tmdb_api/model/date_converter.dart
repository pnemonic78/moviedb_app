import 'package:json_annotation/json_annotation.dart';

class MovieDateTimeConverter implements JsonConverter<DateTime, String> {
  const MovieDateTimeConverter();

  @override
  DateTime fromJson(String json) {
    if ((json == null) || json.isEmpty) return null;
    return DateTime.parse(json);
  }

  @override
  String toJson(DateTime object) => object.toIso8601String();
}