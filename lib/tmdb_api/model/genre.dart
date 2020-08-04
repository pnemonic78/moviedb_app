class Genre {
  final int id;
  final String name;

  const Genre({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  static List<int> toIds(List<Genre> genres) {
    return (genres != null) ? genres.map((g) => g.id).toList() : null;
  }

  factory Genre.fromJson(Map<String, dynamic> json) {
    return Genre(id: json['id'], name: json['name']);
  }
}