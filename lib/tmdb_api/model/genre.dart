import 'package:json_annotation/json_annotation.dart';

part 'genre.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class Genre {
  @JsonKey(name: 'id')
  int id;
  @JsonKey(name: 'name')
  String name;

  Genre({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  factory Genre.fromJson(Map<String, dynamic> json) =>
      (json != null) ? _$GenreFromJson(json) : null;
}
