import 'package:json_annotation/json_annotation.dart';

import 'media.dart';
import 'person.dart';

part 'media_credit.g.dart';

/// A [Person] credit that belongs to a [Media].
@JsonSerializable(explicitToJson: true, createToJson: false)
class MediaCredit extends Person {
  @JsonKey(name: 'credit_id')
  String creditId;
  @JsonKey(includeFromJson: false)
  Media media;

  MediaCredit({
    required Person person,
    required this.creditId,
    required this.media,
  })  : super(
          aliases: person.aliases,
          biography: person.biography,
          birthday: person.birthday,
          birthplace: person.birthplace,
          credits: person.credits,
          deathday: person.deathday,
          externalIds: person.externalIds,
          gender: person.gender,
          homepage: person.homepage,
          imdbId: person.imdbId,
          knownDepartment: person.knownDepartment,
          media: media,
          name: person.name,
          originalName: person.originalName,
          profilePath: person.profilePath,
        );

  /// Creates a [MediaCredit] from a JSON object.
  static MediaCredit? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$MediaCreditFromJson(json);
}
