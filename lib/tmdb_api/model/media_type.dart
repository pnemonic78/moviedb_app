class MediaType {
  final String name;

  const MediaType(this.name);

  static const all = MediaType("all");
  static const movie = MediaType("movie");
  static const tv = MediaType("tv");
  static const person = MediaType("person");

  static const values = [all, movie, tv, person];

  @override
  String toString() {
    return name;
  }

  factory MediaType.valueOf(String json) {
    if (json == null) return null;
    return values.firstWhere((v) => json == v.name);
  }
}
