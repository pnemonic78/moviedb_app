part of 'movie_bloc.dart';

@immutable
class MovieState {
  final bool showAsList;
  final MoviesResponse moviesNowPlaying;

  const MovieState({
    this.showAsList = false,
    this.moviesNowPlaying,
  });

  MovieState copy({bool showAsList, MoviesResponse moviesNowPlaying}) =>
      new MovieState(
        showAsList: showAsList ?? this.showAsList,
        moviesNowPlaying: moviesNowPlaying ?? this.moviesNowPlaying,
      );

  MovieState toggleViewStyle() => copy(showAsList: !this.showAsList);
}

class MovieInitial extends MovieState {
  const MovieInitial() : super(showAsList: false);
}
