class SpokenLanguage {
  String id;
  String name;

  SpokenLanguage({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  factory SpokenLanguage.fromJson(Map<String, dynamic> json) {
    return SpokenLanguage(id: json['iso_639_1'], name: json['name']);
  }
}
