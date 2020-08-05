import 'package:flutter/foundation.dart';

class Media {
  final bool adult;
  final int id;
  final double popularity;

  Media({
    this.adult,
    @required this.id,
    this.popularity,
  }) : assert(id != null);

  /// Creates a [Media] from a JSON object.
  factory Media.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return Media(
      adult: json['adult'],
      id: json['id'],
      popularity: json['popularity']?.toDouble(),
    );
  }
}
