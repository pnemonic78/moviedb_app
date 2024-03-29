part of 'movie_bloc.dart';

@immutable
class MovieState extends Equatable {
  final bool showAsList;
  final MoviesResponse? moviesNowPlaying;
  final MoviesResponse? moviesPopular;
  final MoviesResponse? moviesTopRated;
  final MoviesResponse? moviesUpcoming;
  final Exception? error;

  const MovieState({
    this.showAsList = false,
    this.moviesNowPlaying,
    this.moviesPopular,
    this.moviesTopRated,
    this.moviesUpcoming,
    this.error,
  });

  MovieState copy({
    bool? showAsList,
    MoviesResponse? moviesNowPlaying,
    MoviesResponse? moviesPopular,
    MoviesResponse? moviesTopRated,
    MoviesResponse? moviesUpcoming,
    Exception? error,
  }) =>
      MovieState(
        showAsList: showAsList ?? this.showAsList,
        moviesNowPlaying: moviesNowPlaying ?? this.moviesNowPlaying,
        moviesPopular: moviesPopular ?? this.moviesPopular,
        moviesTopRated: moviesTopRated ?? this.moviesTopRated,
        moviesUpcoming: moviesUpcoming ?? this.moviesUpcoming,
        error: error ?? this.error,
      );

  MovieState toggleViewStyle() => copy(showAsList: !showAsList);

  @override
  List<Object?> get props => [
        showAsList,
        moviesNowPlaying,
        moviesPopular,
        moviesTopRated,
        moviesUpcoming,
        error,
      ];
}

class MovieInitial extends MovieState {
  const MovieInitial() : super(showAsList: false);
}
