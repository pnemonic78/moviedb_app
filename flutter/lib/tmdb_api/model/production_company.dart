import 'package:json_annotation/json_annotation.dart';

part 'production_company.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class ProductionCompany {
  @JsonKey(name: 'id')
  int id;
  @JsonKey(name: 'name')
  String name;
  @JsonKey(name: 'logo_path')
  String? logoPath;
  @JsonKey(name: 'origin_country')
  String? originCountry;

  ProductionCompany({
    required this.id,
    required this.name,
    this.logoPath,
    this.originCountry,
  });

  @override
  String toString() {
    return name;
  }

  static ProductionCompany? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$ProductionCompanyFromJson(json);
}
