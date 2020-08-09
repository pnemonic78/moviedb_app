class Genre {
  int id;
  String name;

  Genre({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  static List<int> toIds(List<Genre> genres) {
    return genres?.map((g) => g.id)?.toList();
  }

  factory Genre.fromJson(Map<String, dynamic> json) {
    return Genre(id: json['id'], name: json['name']);
  }
}
