import 'package:flutter/material.dart';
import 'package:tmdb/credits/cast_screen.dart';
import 'package:tmdb/tmdb_api/cast.dart';

class CastDetailsPage extends StatefulWidget {
  final String title;
  final MovieCast cast;

  CastDetailsPage({Key key, this.title, this.cast})
      : assert(cast != null),
        super(key: key);

  @override
  _CastDetailsPageState createState() => _CastDetailsPageState();
}

class _CastDetailsPageState extends State<CastDetailsPage> {
  @override
  Widget build(BuildContext context) {
    final cast = widget.cast;

    return Scaffold(
      appBar: AppBar(
        title: Text(cast.name),
      ),
      body: CastDetailsWidget(cast: cast),
    );
  }
}
