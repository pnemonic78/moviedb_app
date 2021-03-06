import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:meta/meta.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

part 'movie_event.dart';
part 'movie_state.dart';

class MovieBloc extends Bloc<MovieEvent, MovieState> {
  MovieBloc() : super(MovieInitial());

  @override
  Stream<MovieState> mapEventToState(MovieEvent event) async* {
    if (event is NowPlayingResponseEvent) {
      yield state.copy(moviesNowPlaying: event.response);
      return;
    }
    if (event is PopularResponseEvent) {
      yield state.copy(moviesPopular: event.response);
      return;
    }
    if (event is TopRatedResponseEvent) {
      yield state.copy(moviesTopRated: event.response);
      return;
    }
    if (event is UpcomingResponseEvent) {
      yield state.copy(moviesUpcoming: event.response);
      return;
    }
    if (event is ToggleViewStyleEvent) {
      yield state.toggleViewStyle();
      return;
    }
    if (event is MovieError) {
      yield state.copy(error: event.error);
      return;
    }
    addError(Exception('unsupported event'));
  }
}
