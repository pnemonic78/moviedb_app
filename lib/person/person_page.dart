import 'package:flutter/material.dart';
import 'package:tmdb/di/api_injector_module.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/person/poster_page.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/media_type.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:tmdb/tmdb_api/model/person_cast.dart';
import 'package:tmdb/tmdb_api/model/person_credit.dart';
import 'package:tmdb/tmdb_api/model/person_crew.dart';

import 'person_screen.dart';

class PersonPage extends StatefulWidget {
  final String title;
  Person person;

  PersonPage({Key key, this.title, this.person})
      : assert(person != null),
        super(key: key);

  @override
  _PersonPageState createState() => _PersonPageState();
}

class _PersonPageState extends State<PersonPage> {
  final TMDBApi _api = AppInjectorModule.createApi();
  Person _person;

  Stream<Person> _fetchPerson(BuildContext context) async* {
    if (_person != null) {
      yield _person;
      return;
    }
    _person = await _api.getPerson(context, widget.person);
    yield _person;
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<Person>(
      stream: _fetchPerson(context),
      builder: (BuildContext context, AsyncSnapshot<Person> snapshot) {
        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            final Person person = snapshot.data;
            widget.person = person;
            content = SingleChildScrollView(
              child: PersonDetailsWidget(
                person: person,
                onPosterTap: _onPosterTap,
                onCastTap: _onCastTap,
                onCrewTap: _onCrewTap,
              ),
            );
          } else {
            content = Center(
              child: Icon(
                Icons.error_outline,
                size: errorIconSize,
              ),
            );
          }
        } else {
          content = Center(child: CircularProgressIndicator());
        }

        final person = widget.person;

        return Scaffold(
          appBar: AppBar(
            title: Text(person.name ?? ""),
          ),
          body: content,
        );
      },
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

  /// Function to call when a cast item is tapped.
  void _onCastTap(PersonCast item) {
    setState(() {
      _navigateToMovie(item);
    });
  }

  /// Function to call when a crew item is tapped.
  void _onCrewTap(PersonCrew item) {
    setState(() {
      _navigateToMovie(item);
    });
  }

  /// Navigates to the movie.
  void _navigateToMovie(PersonCredit credit) {
    final type = credit.media.mediaType;
    if (type != MediaType.movie) return; //TODO we only support movies for now.

    Movie movie = Movie.of(credit.media);

    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MovieDetailsHomePage(
                  movie: movie,
                )));
  }
}
