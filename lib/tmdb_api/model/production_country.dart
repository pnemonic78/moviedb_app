class ProductionCountry {
  final String id;
  final String name;

  const ProductionCountry({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  factory ProductionCountry.fromJson(Map<String, dynamic> json) {
    return ProductionCountry(id: json['iso_3166_1'], name: json['name']);
  }
}