class Gender {
  final int _id;

  const Gender(this._id);

  static const unknown = Gender(0);
  static const female = Gender(1);
  static const male = Gender(2);

  static const values = [unknown, female, male];

  factory Gender.valueOf(int json) {
    if (json ==null) return Gender.unknown;
    return values.firstWhere((v) => json == v._id) ?? Gender.unknown;
  }

  factory Gender.fromJson(Map<String, dynamic> json) {
    return Gender.valueOf(json['gender']);
  }
}
