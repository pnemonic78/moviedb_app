import 'package:flutter/material.dart';
import 'now_playing/home_page.dart';

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'The Movie Database Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: NowPlayingHomePage(title: 'The Movie Database'),
    );
  }
}
