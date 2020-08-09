// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'error.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ApiError _$ApiErrorFromJson(Map<String, dynamic> json) {
  return ApiError(
    statusCode: json['status_code'] as int,
    statusMessage: json['status_message'] as String,
    success: json['success'] as bool,
  );
}
