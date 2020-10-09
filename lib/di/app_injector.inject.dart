import 'app_injector.dart' as _i1;
import 'app_injector_module.dart' as _i2;
import 'package:dio/src/dio.dart' as _i3;
import '../tmdb_api/rest_client.dart' as _i4;
import '../tmdb_api/api.dart' as _i5;
import 'dart:async' as _i6;
import '../main/my_app.dart' as _i7;

class AppInjector$Injector implements _i1.AppInjector {
  AppInjector$Injector._(this._appInjectorModule);

  final _i2.AppInjectorModule _appInjectorModule;

  _i3.Dio _singletonDio;

  _i4.RestClient _singletonRestClient;

  _i5.TMDBApi _singletonTMDBApi;

  static _i6.Future<_i1.AppInjector> create(
      _i2.AppInjectorModule appInjectorModule) async {
    final injector = AppInjector$Injector._(appInjectorModule);

    return injector;
  }

  _i7.MyApp _createMyApp() => _appInjectorModule.provideApp();
  _i5.TMDBApi _createTMDBApi() =>
      _singletonTMDBApi ??= _appInjectorModule.provideApi(_createRestClient());
  _i4.RestClient _createRestClient() =>
      _singletonRestClient ??= _appInjectorModule.provideClient(_createDio());
  _i3.Dio _createDio() => _singletonDio ??= _appInjectorModule.provideDio();
  @override
  _i7.MyApp get app => _createMyApp();
  @override
  _i5.TMDBApi get api => _createTMDBApi();
}
