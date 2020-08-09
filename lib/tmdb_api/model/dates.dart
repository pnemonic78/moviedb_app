import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';

part 'dates.g.dart';

DateTime parseDateTime(String s) {
  if ((s == null) || s.isEmpty) return null;
  return DateTime.parse(s);
}

@JsonSerializable(explicitToJson: true, createToJson: false)
class Dates {
  @JsonKey(name: "maximum")
  DateTime maximum;
  @JsonKey(name: "minimum")
  DateTime minimum;

  Dates({@required this.minimum, @required this.maximum});

  /// Creates a [Dates] from a JSON object.
  factory Dates.fromJson(Map<String, dynamic> json) =>
      (json != null) ? _$DatesFromJson(json) : null;
}
