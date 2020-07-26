abstract class MovieCredit {
  final int id;
  final String creditId;
  final String name;
  final String profilePath;
  final int gender;

  const MovieCredit(
      {this.creditId, this.id, this.name, this.profilePath, this.gender});

  @override
  String toString() {
    return '{id: $id, name: "$name"}';
  }
}
