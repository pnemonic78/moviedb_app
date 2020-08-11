import 'package:flutter/foundation.dart';

import 'person_credit.dart';

class PersonCrew extends PersonCredit {
  String department;
  String job;

  PersonCrew({
    @required PersonCredit credit,
    this.department,
    @required this.job,
  })  : assert(job != null),
        super(
          creditId: credit.creditId,
          media: credit.media,
          person: credit.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", department: "$department", job: "$job"}';
  }

  /// Creates a [PersonCrew] from a JSON object.
  factory PersonCrew.fromJson(Map<String, dynamic> json) => (json == null)
      ? null
      : PersonCrew(
          credit: PersonCredit.fromJson(json),
          department: json['department'],
          job: json['job'],
        );
}
