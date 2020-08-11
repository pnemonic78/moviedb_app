import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';
import 'package:tmdb/tmdb_api/model/media.dart';
import 'package:tmdb/tmdb_api/person_credits_response.dart';

import 'external_ids.dart';
import 'gender.dart';
import 'media_type.dart';
import 'person.dart';

part 'media_credit.g.dart';

/// A [Person] credit that belongs to a [Media].
@JsonSerializable(explicitToJson: true, createToJson: false)
class MediaCredit extends Person {
  @JsonKey(name: 'credit_id')
  String creditId;
  @JsonKey(ignore: true)
  Media media;

  MediaCredit({
    @required Person person,
    @required this.creditId,
    @required this.media,
  })  : super(
          aliases: person?.aliases,
          biography: person?.biography,
          birthday: person?.birthday,
          birthplace: person?.birthplace,
          credits: person?.credits,
          deathday: person?.deathday,
          externalIds: person?.externalIds,
          gender: person?.gender,
          homepage: person?.homepage,
          imdbId: person?.imdbId,
          knownDepartment: person?.knownDepartment,
          media: media,
          name: person?.name,
          originalName: person?.originalName,
          profilePath: person?.profilePath,
        );

  /// Creates a [MediaCredit] from a JSON object.
  factory MediaCredit.fromJson(Map<String, dynamic> json) =>
      (json == null) ? null : _$MediaCreditFromJson(json)
        ..media = Media.fromJsonType(json);
}
