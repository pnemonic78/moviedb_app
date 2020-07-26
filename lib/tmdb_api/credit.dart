import 'person.dart';

abstract class MovieCredit extends Person {
  final String creditId;

  const MovieCredit(
    int id,
    String name,
    this.creditId, {
    String profilePath,
    int gender,
  }) : super(id, name, profilePath: profilePath, gender: gender);
}
