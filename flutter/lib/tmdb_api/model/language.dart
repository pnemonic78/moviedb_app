import 'package:json_annotation/json_annotation.dart';

part 'language.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class SpokenLanguage {
  @JsonKey(name: 'iso_639_1')
  String id;
  @JsonKey(name: 'name')
  String name;

  SpokenLanguage({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  factory SpokenLanguage.fromJson(Map<String, dynamic> json) =>
      _$SpokenLanguageFromJson(json);
}
