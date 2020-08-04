class MovieStatus {
  final String _value;

  static const rumored = MovieStatus("Rumored");
  static const planned = MovieStatus("Planned");
  static const inProduction = MovieStatus("In Production");
  static const postProduction = MovieStatus("Post Production");
  static const released = MovieStatus("Released");
  static const canceled = MovieStatus("Canceled");

  static const values = [
    rumored,
    planned,
    inProduction,
    postProduction,
    released,
    canceled
  ];

  const MovieStatus(this._value);

  @override
  String toString() {
    return _value;
  }

  factory MovieStatus.fromJson(String json) {
    return values.firstWhere((v) => json == v._value);
  }
}