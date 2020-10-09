import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:inject/inject.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/now_playing/now_playing.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';

@provide
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final cardTheme = CardTheme.of(context).copyWith(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.all(cardRadius),
      ),
    );

    final TMDBApi api = InjectorWidget.of(context).api;

    return MaterialApp(
      onGenerateTitle: (BuildContext context) =>
          AppLocalizations.of(context).title,
      theme: ThemeData.light().copyWith(
        cardTheme: cardTheme,
      ),
      darkTheme: ThemeData.dark().copyWith(
        cardTheme: cardTheme,
      ),
      home: NowPlayingPage(api),
      localizationsDelegates: [
        const AppLocalizationsDelegate(),
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: [
        const Locale('en', ''), // English, no country code
        const Locale('he', ''), // Hebrew, no country code
      ],
    );
  }
}
