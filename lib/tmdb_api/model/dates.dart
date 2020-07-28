import 'package:flutter/foundation.dart';

class Dates {
  final DateTime maximum;
  final DateTime minimum;

  Dates({@required this.minimum, @required this.maximum});

  /// Creates a [Dates] from a JSON object.
  factory Dates.fromJson(Map<String, dynamic> json) {
    return Dates(
      minimum: DateTime.parse(json['minimum']),
      maximum: DateTime.parse(json['maximum']),
    );
  }
}
