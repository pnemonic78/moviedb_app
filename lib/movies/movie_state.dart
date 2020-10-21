part of 'movie_bloc.dart';

@immutable
class MovieState {
  final bool showAsList;
  final MoviesResponse moviesNowPlaying;
  final MoviesResponse moviesPopular;
  final MoviesResponse moviesTopRated;
  final MoviesResponse moviesUpcoming;

  const MovieState({
    this.showAsList = false,
    this.moviesNowPlaying,
    this.moviesPopular,
    this.moviesTopRated,
    this.moviesUpcoming,
  });

  MovieState copy({
    bool showAsList,
    MoviesResponse moviesNowPlaying,
    MoviesResponse moviesPopular,
    MoviesResponse moviesTopRated,
    MoviesResponse moviesUpcoming,
  }) =>
      new MovieState(
        showAsList: showAsList ?? this.showAsList,
        moviesNowPlaying: moviesNowPlaying ?? this.moviesNowPlaying,
        moviesPopular: moviesPopular ?? this.moviesPopular,
        moviesTopRated: moviesTopRated ?? this.moviesTopRated,
        moviesUpcoming: moviesUpcoming ?? this.moviesUpcoming,
      );

  MovieState toggleViewStyle() => copy(showAsList: !this.showAsList);
}

class MovieInitial extends MovieState {
  const MovieInitial() : super(showAsList: false);
}
