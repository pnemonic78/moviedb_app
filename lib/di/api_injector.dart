import 'package:inject/inject.dart';
import 'package:tmdb/main/my_app.dart';

import 'api_injector.inject.dart' as i;
import 'api_injector_module.dart';

// `flutter pub run build_runner build --delete-conflicting-outputs`
@Injector(const [AppInjectorModule])
abstract class AppInjector {
  /// A generated `async` static function, which takes a [AppInjectorModule] and
  /// asynchronously returns an instance of [AppInjector].
  static final create = i.AppInjector$Injector.create;

  @provide
  MyApp get app;
}