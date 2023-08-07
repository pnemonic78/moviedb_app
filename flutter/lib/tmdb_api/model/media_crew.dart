import 'package:json_annotation/json_annotation.dart';

import 'media_credit.dart';

part 'media_crew.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class MediaCrew extends MediaCredit {
  @JsonKey(name: 'department')
  String? department;
  @JsonKey(name: 'job')
  String job;

  MediaCrew({
    required MediaCredit credit,
    this.department,
    required this.job,
  })  : super(
          creditId: credit.creditId,
          media: credit.media,
          person: credit,
        );

  @override
  String toString() {
    return '{id: $id, name: "$name", department: "$department", job: "$job"}';
  }

  /// Creates a [MediaCrew] from a JSON object.
  static MediaCrew? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$MediaCrewFromJson(json);
}
