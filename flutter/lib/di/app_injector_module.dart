import 'package:dio/dio.dart';
import 'package:injectable/injectable.dart';
import 'package:tmdb/main/my_app.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/api_impl.dart';
import 'package:tmdb/tmdb_api/rest_client.dart';

@module
class AppInjectorModule {
  MyApp get app => MyApp();

  @singleton
  Dio get dio => Dio();

  @lazySingleton
  RestClient client(Dio dio) => RestClient.of(dio);

  @lazySingleton
  TMDBApi api(RestClient client) => TMDBApiImpl(client);
}
