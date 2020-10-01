import 'package:dio/dio.dart';
import 'package:inject/inject.dart';
import 'package:tmdb/main/my_app.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/api_impl.dart';
import 'package:tmdb/tmdb_api/rest_client.dart';

@module
class AppInjectorModule {
  @provide
  @singleton
  MyApp provideApp() => new MyApp();

  @provide
  @singleton
  Dio provideDio() => new Dio();

  @provide
  @singleton
  RestClient provideClient(Dio dio) => new RestClient(dio);

  @provide
  @singleton
  TMDBApi provideApi(RestClient client) => new TMDBApiImpl(client);

  static TMDBApi createApi() {
    final dio = new Dio();
    final client = new RestClient(dio);
    return new TMDBApiImpl(client);
  }
}
