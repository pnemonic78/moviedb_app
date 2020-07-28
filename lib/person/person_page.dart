import 'package:flutter/material.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

import 'person_screen.dart';

class PersonPage extends StatefulWidget {
  final String title;
  final Person person;

  PersonPage({Key key, this.title, this.person})
      : assert(person != null),
        super(key: key);

  @override
  _PersonPageState createState() => _PersonPageState();
}

class _PersonPageState extends State<PersonPage> {
  @override
  Widget build(BuildContext context) {
    final person = widget.person;

    return Scaffold(
      appBar: AppBar(
        title: Text(person.name),
      ),
      body: PersonDetailsWidget(cast: person),
    );
  }
}
