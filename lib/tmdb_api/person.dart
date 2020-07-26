class Person {
  final int id;
  final String name;
  final String profilePath;
  final int gender;

  const Person(
    this.id,
    this.name, {
    this.profilePath,
    this.gender = 0,
  });

  @override
  String toString() {
    return '{id: $id, name: "$name"}';
  }
}
