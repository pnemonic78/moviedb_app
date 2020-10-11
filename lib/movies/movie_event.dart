part of 'movie_bloc.dart';

@immutable
abstract class MovieEvent {}

@immutable
class ToggleViewStyleEvent extends MovieEvent {}

class MoviesResponseEvent extends MovieEvent {
  final MoviesResponse response;

  MoviesResponseEvent(this.response) : super();
}
