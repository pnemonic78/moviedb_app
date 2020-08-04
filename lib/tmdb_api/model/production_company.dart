class ProductionCompany {
  final int id;
  final String name;
  final String logoPath;
  final String originCountry;

  const ProductionCompany(
      {this.id, this.name, this.logoPath, this.originCountry});

  @override
  String toString() {
    return name;
  }

  factory ProductionCompany.fromJson(Map<String, dynamic> json) {
    return ProductionCompany(
        id: json['id'],
        name: json['name'],
        logoPath: json['logo_path'],
        originCountry: json['origin_country']);
  }
}