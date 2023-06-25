import 'package:json_annotation/json_annotation.dart';

import 'person_credit.dart';

part 'person_crew.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class PersonCrew extends PersonCredit {
  @JsonKey(name: 'department')
  String? department;
  @JsonKey(name: 'job')
  String job;

  PersonCrew({
    required PersonCredit credit,
    this.department,
    required this.job,
  }) : super(
          creditId: credit.creditId,
          media: credit.media,
          person: credit.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", department: "$department", job: "$job"}';
  }

  /// Creates a [PersonCrew] from a JSON object.
  static PersonCrew? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$PersonCrewFromJson(json);
}
