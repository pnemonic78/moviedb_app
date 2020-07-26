import 'gender.dart';

class Person {
  final int id;
  final String name;
  final String profilePath;
  final Gender gender;

  const Person(
    this.id,
    this.name, {
    this.profilePath,
    this.gender = Gender.unknown,
  });

  @override
  String toString() {
    return '{id: $id, name: "$name"}';
  }
}
