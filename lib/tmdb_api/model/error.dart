class ApiError {
  int statusCode;
  String statusMessage;
  bool success;

  ApiError({this.statusCode, this.statusMessage, this.success});

  /// Creates a [ApiError] from a JSON object.
  factory ApiError.fromJson(Map<String, dynamic> json) {
    return ApiError(
      statusCode: json['status_code'],
      statusMessage: json['status_message'],
      success: json['success'],
    );
  }
}
