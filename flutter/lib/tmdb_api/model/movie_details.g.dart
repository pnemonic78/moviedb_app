// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'movie_details.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MovieDetails _$MovieDetailsFromJson(Map<String, dynamic> json) {
  return MovieDetails(
    movie: Movie.fromJson(json)!,
    budget: json['budget'] as int,
    genres: (json['genres'] == null)
        ? []
        : (json['genres'] as List)
            .nonNulls
            .map((e) => Genre.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    homepage: json['homepage'] as String,
    imdbId: json['imdb_id'] as String,
    productionCompanies: (json['production_companies'] == null)
        ? []
        : (json['production_companies'] as List)
            .nonNulls
            .map((e) => ProductionCompany.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    productionCountries: (json['production_countries'] == null)
        ? []
        : (json['production_countries'] as List)
            .nonNulls
            .map((e) => ProductionCountry.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    revenue: json['revenue'] as int,
    runtime: json['runtime'] as int,
    spokenLanguages: (json['spoken_languages'] == null)
        ? []
        : (json['spoken_languages'] as List)
            .nonNulls
            .map((e) => SpokenLanguage.fromJson(e as Map<String, dynamic>)!)
            .toList(),
    status: json['status'] as String,
    tagline: json['tagline'] as String,
    credits: CreditsResponse.fromJson(json['credits'] as Map<String, dynamic>?),
  );
}
