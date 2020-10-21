part of 'movie_bloc.dart';

@immutable
abstract class MovieEvent {}

@immutable
class ToggleViewStyleEvent extends MovieEvent {}

abstract class MoviesResponseEvent extends MovieEvent {
  final MoviesResponse response;

  MoviesResponseEvent(this.response) : super();
}

class NowPlayingResponseEvent extends MoviesResponseEvent {
  NowPlayingResponseEvent(response) : super(response);
}

class PopularResponseEvent extends MoviesResponseEvent {
  PopularResponseEvent(response) : super(response);
}

class TopRatedResponseEvent extends MoviesResponseEvent {
  TopRatedResponseEvent(response) : super(response);
}

class UpcomingResponseEvent extends MoviesResponseEvent {
  UpcomingResponseEvent(response) : super(response);
}
