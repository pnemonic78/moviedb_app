import 'package:flutter/material.dart';
import 'package:tmdb/res/strings.dart';
import 'now_playing/home_page.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: R.string.app_title,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: NowPlayingHomePage(title: R.string.app_title),
    );
  }
}
