import 'package:json_annotation/json_annotation.dart';

part 'production_country.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class ProductionCountry {
  @JsonKey(name: 'iso_3166_1')
  String id;
  @JsonKey(name: 'name')
  String name;

  ProductionCountry({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  factory ProductionCountry.fromJson(Map<String, dynamic> json) =>
      _$ProductionCountryFromJson(json);
}
