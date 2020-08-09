class VideoType {
  final String _value;

  const VideoType(this._value);

  static const trailer = VideoType("Trailer");
  static const teaser = VideoType("Teaser");
  static const clip = VideoType("Clip");
  static const featurette = VideoType("Featurette");
  static const behindTheScenes = VideoType("Behind the Scenes");
  static const bloopers = VideoType("Bloopers");

  static const values = [
    trailer,
    teaser,
    clip,
    featurette,
    behindTheScenes,
    bloopers
  ];

  @override
  String toString() {
    return _value;
  }

  factory VideoType.valueOf(String json) {
    return values.firstWhere((v) => json == v._value);
  }
}