import 'package:flutter/foundation.dart';

class PersonExternalIds {
  int id;

  /// IMDB ID
  String imdbId;

  /// Facebook
  String facebookId;

  /// Freebase MID
  String freebaseMid;

  /// Freebase ID
  String freebaseId;

  /// Instagram
  String instagramId;

  /// TVRage ID
  int tvRageId;

  /// Twitter
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
  factory PersonExternalIds.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return PersonExternalIds(
      id: json['id'],
      imdbId: json['imdb_id'],
      facebookId: json['facebook_id'],
      freebaseMid: json['freebase_mid'],
      freebaseId: json['freebase_id'],
      instagramId: json['instagram_id'],
      tvRageId: json['tvrage_id'],
      twitterId: json['twitter_id'],
    );
  }
}
