import 'package:flutter/widgets.dart';
import 'package:tmdb/tmdb_api/api.dart';

class InjectorWidget extends InheritedWidget {
  final TMDBApi api;

  const InjectorWidget({
    super.key,
    required super.child,
    required this.api,
  });

  static InjectorWidget? of(BuildContext context) {
    return context.dependOnInheritedWidgetOfExactType<InjectorWidget>();
  }

  static InjectorWidget get(BuildContext context) {
    return of(context)!;
  }

  @override
  bool updateShouldNotify(InjectorWidget oldWidget) => true;
}
