import 'api_injector.dart' as _i1;
import 'api_injector_module.dart' as _i2;
import '../main/my_app.dart' as _i3;
import 'dart:async' as _i4;

class AppInjector$Injector implements _i1.AppInjector {
  AppInjector$Injector._(this._appInjectorModule);

  final _i2.AppInjectorModule _appInjectorModule;

  _i3.MyApp _singletonMyApp;

  static _i4.Future<_i1.AppInjector> create(
      _i2.AppInjectorModule appInjectorModule) async {
    final injector = AppInjector$Injector._(appInjectorModule);

    return injector;
  }

  _i3.MyApp _createMyApp() =>
      _singletonMyApp ??= _appInjectorModule.provideApp();
  @override
  _i3.MyApp get app => _createMyApp();
}
