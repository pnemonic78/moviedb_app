part of 'main_bloc.dart';

@immutable
class MainState {
  final bool showAsList;

  const MainState({
    this.showAsList = false,
  });

  MainState copy({bool showAsList}) =>
      new MainState(showAsList: showAsList ?? this.showAsList);

  MainState toggleViewStyle() => copy(showAsList: !this.showAsList);
}

class MainInitial extends MainState {
  const MainInitial() : super(showAsList: false);
}
