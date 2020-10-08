import 'api_injector.dart' as _i1;
import 'api_injector_module.dart' as _i2;
import 'package:dio/src/dio.dart' as _i3;
import '../tmdb_api/rest_client.dart' as _i4;
import '../tmdb_api/api.dart' as _i5;
import '../main/my_app.dart' as _i6;
import 'dart:async' as _i7;

class AppInjector$Injector implements _i1.AppInjector {
  AppInjector$Injector._(this._appInjectorModule);

  final _i2.AppInjectorModule _appInjectorModule;

  _i3.Dio _singletonDio;

  _i4.RestClient _singletonRestClient;

  _i5.TMDBApi _singletonTMDBApi;

  _i6.MyApp _singletonMyApp;

  static _i7.Future<_i1.AppInjector> create(
      _i2.AppInjectorModule appInjectorModule) async {
    final injector = AppInjector$Injector._(appInjectorModule);

    return injector;
  }

  _i6.MyApp _createMyApp() =>
      _singletonMyApp ??= _appInjectorModule.provideApp(_createTMDBApi());
  _i5.TMDBApi _createTMDBApi() =>
      _singletonTMDBApi ??= _appInjectorModule.provideApi(_createRestClient());
  _i4.RestClient _createRestClient() =>
      _singletonRestClient ??= _appInjectorModule.provideClient(_createDio());
  _i3.Dio _createDio() => _singletonDio ??= _appInjectorModule.provideDio();
  @override
  _i6.MyApp get app => _createMyApp();
}
