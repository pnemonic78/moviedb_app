class MovieImageType {
  final String _value;

  const MovieImageType(this._value);

  static const Trailer = MovieImageType("Trailer");
  static const Teaser = MovieImageType("Teaser");
  static const Clip = MovieImageType("Clip");
  static const Featurette = MovieImageType("Featurette");
  static const OpeningCredits = MovieImageType("Opening Credits");
  static const BehindTheScenes = MovieImageType("Behind the Scenes");
  static const Bloopers = MovieImageType("Bloopers");
  static const Recap = MovieImageType("Recap");

  static const values = [
    Trailer,
    Teaser,
    Clip,
    Featurette,
    OpeningCredits,
    BehindTheScenes,
    Bloopers,
    Recap,
  ];

  @override
  String toString() {
    return _value;
  }

  static MovieImageType? valueOf(String? json) {
    return values.firstWhere((v) => json == v._value);
  }
}
