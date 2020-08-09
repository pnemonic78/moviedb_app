import 'model/image.dart';

class ImagesResponse {
  int id;
  List<MovieImage> backdrops;
  List<MovieImage> posters;

  ImagesResponse({
    this.id,
    this.backdrops,
    this.posters,
  });

  /// Creates a [ImagesResponse] from a JSON object.
  factory ImagesResponse.fromJson(Map<String, dynamic> json) {
    var list = json['backdrops'] as List;
    List<MovieImage> backdrops =
        list.map((i) => MovieImage.fromJson(i)).toList();

    list = json['posters'] as List;
    List<MovieImage> posters = list.map((i) => MovieImage.fromJson(i)).toList();

    return ImagesResponse(
      id: json['id'],
      backdrops: backdrops,
      posters: posters,
    );
  }
}
