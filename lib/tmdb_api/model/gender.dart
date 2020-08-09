import 'package:json_annotation/json_annotation.dart';

enum Gender {
  @JsonValue(0)
  unknown,
  @JsonValue(1)
  female,
  @JsonValue(2)
  male,
}
