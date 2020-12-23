import 'package:json_annotation/json_annotation.dart';

enum MediaType {
  @JsonValue("all")
  all,
  @JsonValue("movie")
  movie,
  @JsonValue("tv")
  tv,
  @JsonValue("person")
  person,
}
