import 'package:flutter/material.dart';
import 'package:tmdb/credits/cast_screen.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

class CastDetailsPage extends StatefulWidget {
  final String title;
  final Person person;

  CastDetailsPage({Key key, this.title, this.person})
      : assert(person != null),
        super(key: key);

  @override
  _CastDetailsPageState createState() => _CastDetailsPageState();
}

class _CastDetailsPageState extends State<CastDetailsPage> {
  @override
  Widget build(BuildContext context) {
    final person = widget.person;

    return Scaffold(
      appBar: AppBar(
        title: Text(person.name),
      ),
      body: CastDetailsWidget(cast: person),
    );
  }
}
