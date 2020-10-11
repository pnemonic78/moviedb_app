part of 'movie_bloc.dart';

@immutable
class MovieState {
  final bool showAsList;

  const MovieState({
    this.showAsList = false,
  });

  MovieState copy({bool showAsList}) =>
      new MovieState(showAsList: showAsList ?? this.showAsList);

  MovieState toggleViewStyle() => copy(showAsList: !this.showAsList);
}

class MovieInitial extends MovieState {
  const MovieInitial() : super(showAsList: false);
}
