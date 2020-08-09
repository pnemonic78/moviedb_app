import 'package:flutter/foundation.dart';
import 'package:json_annotation/json_annotation.dart';

part 'external_ids.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class PersonExternalIds {
  @JsonKey(name: 'id')
  int id;

  /// IMDB ID
  @JsonKey(name: 'imdb_id')
  String imdbId;

  /// Facebook
  @JsonKey(name: 'facebook_id')
  String facebookId;

  /// Freebase MID
  @JsonKey(name: 'freebase_mid')
  String freebaseMid;

  /// Freebase ID
  @JsonKey(name: 'freebase_id')
  String freebaseId;

  /// Instagram
  @JsonKey(name: 'instagram_id')
  String instagramId;

  /// TVRage ID
  @JsonKey(name: 'tvrage_id')
  int tvRageId;

  /// Twitter
  @JsonKey(name: 'twitter_id')
  String twitterId;

  PersonExternalIds({
    @required this.id,
    this.imdbId,
    this.facebookId,
    this.freebaseMid,
    this.freebaseId,
    this.instagramId,
    this.tvRageId,
    this.twitterId,
  });

  /// Creates a [PersonExternalIds] from a JSON object.
  factory PersonExternalIds.fromJson(Map<String, dynamic> json) =>
      (json != null) ? _$PersonExternalIdsFromJson(json) : null;
}
