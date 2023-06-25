import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tmdb/tmdb_api/movies_response.dart';

part 'movie_event.dart';
part 'movie_state.dart';

class MovieBloc extends Bloc<MovieEvent, MovieState> {
  MovieBloc() : super(const MovieInitial()) {
    on<NowPlayingResponseEvent>(
        (event, emit) => emit(state.copy(moviesNowPlaying: event.response)));
    on<PopularResponseEvent>(
        (event, emit) => emit(state.copy(moviesPopular: event.response)));
    on<TopRatedResponseEvent>(
        (event, emit) => emit(state.copy(moviesTopRated: event.response)));
    on<UpcomingResponseEvent>(
        (event, emit) => emit(state.copy(moviesUpcoming: event.response)));
    on<ToggleViewStyleEvent>((event, emit) => emit(state.toggleViewStyle()));
    on<MovieError>((event, emit) => emit(state.copy(error: event.error)));
  }
}
