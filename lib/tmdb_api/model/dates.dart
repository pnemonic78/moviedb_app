import 'package:flutter/foundation.dart';

DateTime parseDateTime(String s) {
  if ((s == null) || s.isEmpty) return null;
  return DateTime.parse(s);
}

class Dates {
  final DateTime maximum;
  final DateTime minimum;

  Dates({@required this.minimum, @required this.maximum});

  /// Creates a [Dates] from a JSON object.
  factory Dates.fromJson(Map<String, dynamic> json) {
    return Dates(
      minimum: parseDateTime(json['minimum']),
      maximum: parseDateTime(json['maximum']),
    );
  }
}
