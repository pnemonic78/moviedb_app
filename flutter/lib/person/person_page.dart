import 'package:flutter/material.dart';
import 'package:tmdb/di/injector_inherited.dart';
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

  PersonPage({super.key, required this.title, required this.person});

  @override
  State<PersonPage> createState() => _PersonPageState();
}

class _PersonPageState extends State<PersonPage> {
  Person? _person;

  Stream<Person> _fetchPerson(BuildContext context) async* {
    Person? person = _person;
    if (person != null) {
      yield person;
      return;
    }
    final TMDBApi api = InjectorWidget.get(context).api;
    person = await api.getPerson(context, widget.person);
    _person = person;
    yield person;
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<Person>(
      stream: _fetchPerson(context),
      builder: (BuildContext context, AsyncSnapshot<Person> snapshot) {
        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            final Person person = snapshot.data!;
            widget.person = person;
            content = SingleChildScrollView(
              child: PersonDetailsWidget(
                person: person,
                onTapPoster: _onTapPoster,
                onCastTap: _onTapCast,
                onCrewTap: _onTapCrew,
              ),
            );
          } else {
            content = const Center(
              child: Icon(
                Icons.error_outline,
                size: errorIconSize,
              ),
            );
          }
        } else {
          content = const Center(child: CircularProgressIndicator());
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
  void _onTapPoster(Person person) {
    setState(() {
      _navigateToPoster(person);
    });
  }

  /// Navigates to the movie poster.
  void _navigateToPoster(Person person) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => PersonPosterPage(person: person),
      ),
    );
  }

  /// Function to call when a cast item is tapped.
  void _onTapCast(PersonCast item) {
    setState(() {
      _navigateToMovie(item);
    });
  }

  /// Function to call when a crew item is tapped.
  void _onTapCrew(PersonCrew item) {
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
