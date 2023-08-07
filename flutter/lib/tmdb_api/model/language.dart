import 'package:json_annotation/json_annotation.dart';

part 'language.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class SpokenLanguage {
  @JsonKey(name: 'iso_639_1')
  String id;
  @JsonKey(name: 'name')
  String name;

  SpokenLanguage({required this.id, required this.name});

  @override
  String toString() {
    return name;
  }

  static SpokenLanguage? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$SpokenLanguageFromJson(json);
}
