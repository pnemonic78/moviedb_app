import 'package:flutter/foundation.dart';

class Media {
  final bool adult;
  final int id;
  final double popularity;

  const Media({
    this.adult,
    @required this.id,
    this.popularity,
  }) : assert(id != null);
}
