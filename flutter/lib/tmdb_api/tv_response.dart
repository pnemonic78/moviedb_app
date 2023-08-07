import 'package:json_annotation/json_annotation.dart';

import 'model/tv.dart';

part 'tv_response.g.dart';

@JsonSerializable(explicitToJson: true, createToJson: false)
class TelevisionResponse {
  @JsonKey(name: 'results')
  List<Television> results;
  @JsonKey(name: 'page')
  int page;
  @JsonKey(name: 'total_pages')
  int totalPages;
  @JsonKey(name: 'total_results')
  int totalResults;

  TelevisionResponse({
    required this.results,
    this.page = 1,
    this.totalPages = 1,
    required this.totalResults,
  });

  /// Creates a [TelevisionResponse] from a JSON object.
  static TelevisionResponse? fromJson(Map<String, dynamic>? json) =>
      (json == null) ? null : _$TelevisionResponseFromJson(json);
}
