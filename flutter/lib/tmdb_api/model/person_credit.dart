import 'package:json_annotation/json_annotation.dart';

import 'media.dart';
import 'person.dart';

part 'person_credit.g.dart';

/// A [Media] credit that belongs to a [Person].
@JsonSerializable(explicitToJson: true, createToJson: false)
class PersonCredit {
  @JsonKey(name: 'credit_id')
  String creditId;
  @JsonKey(includeFromJson: false)
  Media media;
  @JsonKey(includeFromJson: false)
  Person person;

  PersonCredit({
    required this.creditId,
    required this.media,
    required this.person,
  });

  @override
  String toString() {
    return person.toString();
  }

  /// Creates a [PersonCredit] from a JSON object.
  static PersonCredit? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$PersonCreditFromJson(json);

  String? title() {
    return media.getTitle() ?? person.getTitle();
  }
}
