import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:inject/inject.dart';
import 'package:tmdb/now_playing/now_playing.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';

import 'main_bloc.dart';

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
      home: BlocProvider(
        create: (_) => MainBloc(),
        child: NowPlayingPage(),
      ),
      localizationsDelegates: [
        const AppLocalizationsDelegate(),
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: AppLocalizationsDelegate.locales,
    );

    return BlocProvider(
      create: (_) => MainBloc(),
      child: BlocBuilder<MainBloc, MainState>(
        builder: (context, state) {
          return app;
        },
      ),
    );
  }
}
