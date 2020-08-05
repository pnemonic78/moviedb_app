import 'package:flutter/foundation.dart';

import 'person_credit.dart';

class PersonCrew extends PersonCredit {
  final PersonCredit credit;
  final String department;
  final String job;

  PersonCrew({
    @required this.credit,
    this.department,
    @required this.job,
  })  : assert(job != null),
        super(
          creditId: credit.creditId,
          media: credit.media,
          originCountry: credit.originCountry,
          person: credit.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", department: "$department", job: "$job"}';
  }

  /// Creates a [PersonCrew] from a JSON object.
  factory PersonCrew.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return PersonCrew(
      credit: PersonCredit.fromJson(json),
      department: json['department'],
      job: json['job'],
    );
  }
}
