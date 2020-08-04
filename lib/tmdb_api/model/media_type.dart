enum MediaType {
  /// All movies, TV shows and people.
  all,

  /// Movies.
  movie,

  /// TV shows.
  tv,

  /// People.
  person,
}

MediaType MediaType_fromJson(String value) {
  switch (value) {
    case "all":
      return MediaType.all;
    case "movie":
      return MediaType.movie;
    case "tv":
      return MediaType.tv;
    case "person":
      return MediaType.person;
    default:
      return MediaType.all;
  }
}
