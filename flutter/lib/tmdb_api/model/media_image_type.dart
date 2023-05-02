class MovieImageType {
  final String _value;

  const MovieImageType(this._value);

  static const trailer = MovieImageType("Trailer");
  static const teaser = MovieImageType("Teaser");
  static const clip = MovieImageType("Clip");
  static const featurette = MovieImageType("Featurette");
  static const behindTheScenes = MovieImageType("Behind the Scenes");
  static const bloopers = MovieImageType("Bloopers");

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

  factory MovieImageType.valueOf(String json) {
    return values.firstWhere((v) => json == v._value);
  }
}
