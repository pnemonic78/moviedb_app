import 'package:flutter/widgets.dart';
import 'package:tmdb/tmdb_api/api.dart';

class InjectorWidget extends InheritedWidget {
  final TMDBApi api;

  InjectorWidget({
    Key key,
    @required Widget child,
    @required this.api,
  })  : assert(child != null),
        assert(api != null),
        super(key: key, child: child);

  static InjectorWidget of(BuildContext context) {
    return context.dependOnInheritedWidgetOfExactType<InjectorWidget>();
  }

  @override
  bool updateShouldNotify(InjectorWidget old) => true;
}
