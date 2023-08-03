import 'app_injector.dart' as i1;
import 'app_injector_module.dart' as i2;
import 'package:dio/dio.dart' as i3;
import '../tmdb_api/rest_client.dart' as i4;
import '../tmdb_api/api.dart' as i5;
import 'dart:async' as i6;
import '../main/my_app.dart' as i7;

class AppInjector$Injector implements i1.AppInjector {
  AppInjector$Injector._(this._appInjectorModule);

  final i2.AppInjectorModule _appInjectorModule;

  i3.Dio? _singletonDio;

  i4.RestClient? _singletonRestClient;

  i5.TMDBApi? _singletonTMDBApi;

  static i6.Future<i1.AppInjector> create(
      i2.AppInjectorModule appInjectorModule) async {
    final injector = AppInjector$Injector._(appInjectorModule);

    return injector;
  }

  i7.MyApp _createMyApp() => _appInjectorModule.app;
  i5.TMDBApi _createTMDBApi() =>
      _singletonTMDBApi ??= _appInjectorModule.api(_createRestClient());
  i4.RestClient _createRestClient() =>
      _singletonRestClient ??= _appInjectorModule.client(_createDio());
  i3.Dio _createDio() => _singletonDio ??= _appInjectorModule.dio;
  @override
  i7.MyApp get app => _createMyApp();
  @override
  i5.TMDBApi get api => _createTMDBApi();
}
