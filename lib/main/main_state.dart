part of 'main_bloc.dart';

@immutable
class MainState {
  final bool showAsList;

  const MainState({
    this.showAsList = false,
  });
}

class MainInitial extends MainState {
  const MainInitial() : super(showAsList: false);
}
