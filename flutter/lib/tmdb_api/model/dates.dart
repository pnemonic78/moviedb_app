import 'package:json_annotation/json_annotation.dart';

part 'dates.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class Dates {
  @JsonKey(name: "maximum")
  DateTime? maximum;
  @JsonKey(name: "minimum")
  DateTime? minimum;

  Dates({required this.minimum, required this.maximum});

  /// Creates a [Dates] from a JSON object.
  static Dates? fromJson(Map<String, dynamic>? json) =>
    (json == null) ? null : _$DatesFromJson(json);
}
