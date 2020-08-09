import 'package:json_annotation/json_annotation.dart';

part 'error.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class ApiError {
  @JsonKey(name: 'status_code')
  int statusCode;
  @JsonKey(name: 'status_message')
  String statusMessage;
  @JsonKey(name: 'success')
  bool success;

  ApiError({this.statusCode, this.statusMessage, this.success});

  /// Creates a [ApiError] from a JSON object.
  factory ApiError.fromJson(Map<String, dynamic> json) =>
      _$ApiErrorFromJson(json);
}
