import 'package:flutter/material.dart';
import 'package:pinch_zoom/pinch_zoom.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

class PersonPosterPage extends StatelessWidget {
  final Person person;

  const PersonPosterPage({super.key, required this.person});

  @override
  Widget build(BuildContext context) {
    final poster = TMDBApi.generateProfile(
      profilePath: person.profilePath,
      profileWidth: double.infinity,
      profileHeight: double.infinity,
      fit: BoxFit.contain,
    );
    final posterWidget = PinchZoom(child: poster);

    return Scaffold(
      appBar: AppBar(
        title: Text(person.getTitle() ?? person.name),
      ),
      body: Padding(
        padding: paddingAll_8,
        child: posterWidget,
      ),
    );
  }
}
