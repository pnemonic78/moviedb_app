import 'package:tmdb/tmdb_api/model/credit.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

import 'external_ids.dart';
import 'gender.dart';

class MovieCast extends MovieCredit {
  final int castId;
  final String character;
  final int order;

  const MovieCast(
    final int id,
    final String name,
    final String creditId,
    this.castId, {
    // person
    final List<String> aliases,
    final String profilePath,
    final Gender gender,
    final DateTime birthday,
    final DateTime deathday,
    final String knownDepartment,
    final String biography,
    final double popularity,
    final String birthplace,
    final bool adult,
    final String imdbId,
    final String homepage,
    final PersonExternalIds externalIds,
    // credit
    final String backdropPath,
    final int episodeCount,
    final DateTime firstAirDate,
    final List<int> genreIds,
    final String mediaType,
    final List<String> originCountry,
    final String originalLanguage,
    final String originalName,
    final String originalTitle,
    final String overview,
    final String posterPath,
    final DateTime releaseDate,
    final String title,
    final bool video,
    final double voteAverage,
    final int voteCount,
    // cast
    this.character,
    this.order,
  }) : super(
          id,
          name,
          creditId,
          // person
          aliases: aliases,
          profilePath: profilePath,
          gender: gender,
          birthday: birthday,
          deathday: deathday,
          knownDepartment: knownDepartment,
          biography: biography,
          popularity: popularity,
          birthplace: birthplace,
          adult: adult,
          imdbId: imdbId,
          homepage: homepage,
          externalIds: externalIds,
          // credit
          backdropPath: backdropPath,
          episodeCount: episodeCount,
          firstAirDate: firstAirDate,
          genreIds: genreIds,
          mediaType: mediaType,
          originCountry: originCountry,
          originalLanguage: originalLanguage,
          originalName: originalName,
          originalTitle: originalTitle,
          overview: overview,
          posterPath: posterPath,
          releaseDate: releaseDate,
          title: title,
          video: video,
          voteAverage: voteAverage,
          voteCount: voteCount,
        );

  @override
  String toString() {
    return '{id: $id, name: "$name", character: "$character"}';
  }

  /// Creates a [MovieCast] from a JSON object.
  factory MovieCast.fromJson(Map<String, dynamic> json) {
    if (json == null) return null;

    final credit = MovieCredit.fromJson(json);
    final Person person = credit;

    return MovieCast(
      credit.id,
      credit.name,
      credit.creditId,
      json['cast_id'],

      // person
      aliases: person.aliases,
      profilePath: person.profilePath,
      gender: person.gender,
      birthday: person.birthday,
      deathday: person.deathday,
      knownDepartment: person.knownDepartment,
      biography: person.biography,
      popularity: person.popularity,
      birthplace: person.birthplace,
      adult: person.adult,
      imdbId: person.imdbId,
      homepage: person.homepage,
      externalIds: person.externalIds,

      // credit
      backdropPath: credit.backdropPath,
      episodeCount: credit.episodeCount,
      firstAirDate: credit.firstAirDate,
      genreIds: credit.genreIds,
      mediaType: credit.mediaType,
      originCountry: credit.originCountry,
      originalLanguage: credit.originalLanguage,
      originalName: credit.originalName,
      originalTitle: credit.originalTitle,
      overview: credit.overview,
      posterPath: credit.posterPath,
      releaseDate: credit.releaseDate,
      title: credit.title,
      video: credit.video,
      voteAverage: credit.voteAverage,
      voteCount: credit.voteCount,

      // cast
      character: json['character'],
      order: json['order'],
    );
  }
}
