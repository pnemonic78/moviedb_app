import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:tmdb/now_playing/now_playing_grid.dart';

import 'res/dimens.dart';
import 'res/i18n.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final cardTheme = CardTheme.of(context).copyWith(
        shape:
            RoundedRectangleBorder(borderRadius: BorderRadius.all(cardRadius)));

    return MaterialApp(
      onGenerateTitle: (BuildContext context) =>
          AppLocalizations.of(context).title,
      theme: ThemeData.light().copyWith(
        cardTheme: cardTheme,
      ),
      darkTheme: ThemeData.dark().copyWith(
        cardTheme: cardTheme,
      ),
      home: NowPlayingGridPage(),
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
