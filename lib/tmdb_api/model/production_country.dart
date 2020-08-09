class ProductionCountry {
  String id;
  String name;

  ProductionCountry({this.id, this.name});

  @override
  String toString() {
    return name;
  }

  factory ProductionCountry.fromJson(Map<String, dynamic> json) {
    return ProductionCountry(id: json['iso_3166_1'], name: json['name']);
  }
}
