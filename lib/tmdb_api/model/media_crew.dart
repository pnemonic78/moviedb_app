import 'package:flutter/foundation.dart';

import 'media_credit.dart';

class MediaCrew extends MediaCredit {
  String department;
  String job;

  MediaCrew({
    MediaCredit credit,
    this.department,
    @required this.job,
  })  : assert(job != null),
        super(
          creditId: credit?.creditId,
          media: credit?.media,
          person: credit?.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", department: "$department", job: "$job"}';
  }

  /// Creates a [MediaCrew] from a JSON object.
  factory MediaCrew.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return MediaCrew(
      credit: MediaCredit.fromJson(json),
      department: json['department'],
      job: json['job'],
    );
  }
}
