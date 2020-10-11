part of 'movie_bloc.dart';

@immutable
abstract class MovieEvent {}

@immutable
class ToggleViewStyleEvent extends MovieEvent {}
