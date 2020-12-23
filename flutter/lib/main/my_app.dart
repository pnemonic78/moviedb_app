import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:inject/inject.dart';
import 'package:tmdb/movies/bloc/movie_bloc.dart';
import 'package:tmdb/movies/movies_all_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';

@provide
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final cardTheme = CardTheme.of(context).copyWith(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.all(cardRadius),
      ),
    );

    final app = MaterialApp(
      onGenerateTitle: (BuildContext context) =>
          AppLocalizations.of(context).title,
      theme: ThemeData.light().copyWith(
        cardTheme: cardTheme,
      ),
      darkTheme: ThemeData.dark().copyWith(
        cardTheme: cardTheme,
      ),
      home: MoviesAllPage(),
      localizationsDelegates: [
        const AppLocalizationsDelegate(),
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: AppLocalizationsDelegate.locales,
    );

    return BlocProvider(
      create: (_) => MovieBloc(),
      child: BlocBuilder<MovieBloc, MovieState>(
        builder: (context, state) => app,
      ),
    );
  }
}
