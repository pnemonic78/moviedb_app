import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

part 'movie_event.dart';
part 'movie_state.dart';

class MovieBloc extends Bloc<MovieEvent, MovieState> {
  MovieBloc() : super(MovieInitial());

  @override
  Stream<MovieState> mapEventToState(MovieEvent event) async* {
    if (event is MoviesResponseEvent) {
      yield state.copy(moviesNowPlaying: event.response);
      return;
    }
    if (event is ToggleViewStyleEvent) {
      yield state.toggleViewStyle();
      return;
    }
    addError(Exception('unsupported event'));
  }
}
