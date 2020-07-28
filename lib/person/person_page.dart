import 'package:flutter/material.dart';
import 'package:tmdb/person/poster_page.dart';
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
      body: PersonDetailsWidget(
        person: person,
        onPosterTap: _onPosterTap,
      ),
    );
  }

  /// Function to call when a poster [Image] is tapped.
  void _onPosterTap(Person person) {
    setState(() {
      _navigateToPoster(person);
    });
  }

  /// Navigates to the movie poster.
  void _navigateToPoster(Person person) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => PersonPosterPage(
                  person: person,
                )));
  }
}
