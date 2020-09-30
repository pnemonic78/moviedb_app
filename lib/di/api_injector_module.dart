import 'package:inject/inject.dart';
import 'package:tmdb/main/my_app.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/api_impl.dart';

@module
class AppInjectorModule {
  @provide
  @singleton
  MyApp provideApp() => new MyApp();

  @provide
  @singleton
  TMDBApi provideApi() => new TMDBApiImpl();
}
