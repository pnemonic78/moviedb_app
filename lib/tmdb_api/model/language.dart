class SpokenLanguage {
  final String id;
  final String name;

  const SpokenLanguage({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  factory SpokenLanguage.fromJson(Map<String, dynamic> json) {
    return SpokenLanguage(id: json['iso_639_1'], name: json['name']);
  }
}