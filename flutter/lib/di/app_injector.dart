import 'package:injectable/injectable.dart';
import 'package:tmdb/main/my_app.dart';
import 'package:tmdb/tmdb_api/api.dart';

import 'app_injector.inject.dart' as i;
import 'app_injector_module.dart';

// `flutter pub run build_runner build --delete-conflicting-outputs`
// Generated files can be found at ".dart_tool/build/generated".
//@injectable
//(const [AppInjectorModule])
abstract class AppInjector {
  /// A generated `async` static function, which takes a [AppInjectorModule] and
  /// asynchronously returns an instance of [AppInjector].
  static const create = i.AppInjector$Injector.create;

  // @provide
  MyApp get app;

  // @provide
  TMDBApi get api;
}