class Gender {
  final int id;

  static const unknown = Gender(0);
  static const female = Gender(1);
  static const male = Gender(2);

  static const values = [unknown, female, male];

  const Gender(this.id);

  factory Gender.fromJson(int json) {
    if (json ==null) return Gender.unknown;
    return values.firstWhere((v) => json == v.id) ?? Gender.unknown;
  }
}
