import 'package:flutter/material.dart';
import 'package:tmdb/movie_details/home_page.dart';
import 'package:tmdb/person/poster_page.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/cast.dart';
import 'package:tmdb/tmdb_api/model/credit.dart';
import 'package:tmdb/tmdb_api/model/crew.dart';
import 'package:tmdb/tmdb_api/model/movie.dart';
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
  final _api = TMDBApi();
  Person _person;

  Stream<Person> _fetchPerson() async* {
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
      stream: _fetchPerson(),
      builder: (BuildContext context, AsyncSnapshot<Person> snapshot) {
        Widget content;
        if ((snapshot.connectionState == ConnectionState.done) &&
            snapshot.hasData) {
          final Person person = snapshot.data;
          content = PersonDetailsWidget(
            person: person,
            onPosterTap: _onPosterTap,
            onCastTap: _onCastTap,
            onCrewTap: _onCrewTap,
          );
        } else {
          content = Center(child: CircularProgressIndicator());
        }

        final person = widget.person;

        return Scaffold(
          appBar: AppBar(
            title: Text(person.name),
          ),
          body: SingleChildScrollView(
            child: content,
          ),
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
  void _onCastTap(MovieCast item) {
    setState(() {
      _navigateToMovie(item);
    });
  }

  /// Function to call when a crew item is tapped.
  void _onCrewTap(MovieCrew item) {
    setState(() {
      _navigateToMovie(item);
    });
  }

  /// Navigates to the movie.
  void _navigateToMovie(MovieCredit credit) {
    final type = credit.mediaType;
    if (type != "movie") return; //TODO we only support movies for now.

    Movie movie = Movie(
      adult: credit.adult,
      backdropPath: credit.backdropPath,
      genreIds: credit.genreIds,
      id: credit.id,
      originalLanguage: credit.originalLanguage,
      originalTitle: credit.originalTitle,
      overview: credit.overview,
      popularity: credit.popularity,
      posterPath: credit.posterPath,
      //FIXME releaseDate: credit.releaseDate,
      title: credit.title,
      video: credit.video,
      voteAverage: credit.voteAverage,
      voteCount: credit.voteCount,
    );

    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => MovieDetailsHomePage(
                  movie: movie,
                )));
  }
}
