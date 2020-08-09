import 'media_credit.dart';

class MediaCast extends MediaCredit {
  int castId;
  String character;
  int order;

  MediaCast({
    this.castId,
    this.character,
    MediaCredit credit,
    this.order,
  }) : super(
          creditId: credit?.creditId,
          media: credit?.media,
          person: credit?.person,
        );

  @override
  String toString() {
    return '{id: ${person.id}, name: "${person.name}", character: "$character"}';
  }

  /// Creates a [MediaCast] from a JSON object.
  factory MediaCast.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    return MediaCast(
      castId: json['cast_id'],
      character: json['character'],
      credit: MediaCredit.fromJson(json),
      order: json['order'],
    );
  }
}
