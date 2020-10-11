import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'main_event.dart';
part 'main_state.dart';

class MainBloc extends Bloc<MainEvent, MainState> {
  MainBloc() : super(MainInitial());

  @override
  Stream<MainState> mapEventToState(MainEvent event) async* {
    if (event is ToggleViewStyleEvent) {
      yield state.toggleViewStyle();
      return;
    }
    addError(Exception('unsupported event'));
  }
}
