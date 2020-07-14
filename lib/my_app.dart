import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

import 'now_playing/home_page.dart';
import 'res/i18n.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      onGenerateTitle: (BuildContext context) => AppLocalizations.of(context).title,
      theme: ThemeData.dark(),
      home: NowPlayingHomePage(),
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
