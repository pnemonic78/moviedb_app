part of 'movie_bloc.dart';

@immutable
abstract class MovieEvent extends Equatable {
  const MovieEvent();

  @override
  List<Object> get props => [];
}

@immutable
class ToggleViewStyleEvent extends MovieEvent {
  const ToggleViewStyleEvent() : super();
}

abstract class MoviesResponseEvent extends MovieEvent {
  final MoviesResponse response;

  const MoviesResponseEvent(this.response) : super();

  @override
  List<Object> get props => [response];
}

class NowPlayingResponseEvent extends MoviesResponseEvent {
  const NowPlayingResponseEvent(response) : super(response);
}

class PopularResponseEvent extends MoviesResponseEvent {
  const PopularResponseEvent(response) : super(response);
}

class TopRatedResponseEvent extends MoviesResponseEvent {
  const TopRatedResponseEvent(response) : super(response);
}

class UpcomingResponseEvent extends MoviesResponseEvent {
  const UpcomingResponseEvent(response) : super(response);
}

class MovieError extends MovieEvent {
  final Exception error;

  const MovieError(this.error) : super();

  @override
  List<Object> get props => [error];
}
