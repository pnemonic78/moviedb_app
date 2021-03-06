import 'package:flutter/material.dart';
import 'package:tmdb/di/injector_inherited.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/movie_details.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';

import 'video_tile.dart';

class VideosList extends StatelessWidget {
  final MovieDetails movie;
  final ValueChanged<MovieVideo> onTap;

  VideosList({Key key, @required this.movie, this.onTap})
      : assert(movie != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<VideosResponse>(
      future: _fetchVideos(context),
      builder: (BuildContext context, AsyncSnapshot<VideosResponse> snapshot) {
        Widget content;
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            content = Container(
              height: thumbnailHeight + 62,
              child: ListView(
                scrollDirection: Axis.horizontal,
                children: _buildList(context, snapshot.data.results),
              ),
            );
          } else {
            content = Container();
          }
        } else {
          content = Center(child: CircularProgressIndicator());
        }

        return content;
      },
    );
  }

  Future<VideosResponse> _fetchVideos(BuildContext context) async {
    final TMDBApi api = InjectorWidget.of(context).api;
    return api.getMovieVideos(context, movie);
  }

  List<Widget> _buildList(BuildContext context, List<MovieVideo> videos) {
    final list = <Widget>[];

    for (var video in videos) {
      list.add(VideoTile(
        video: video,
        onTap: onTap,
      ));
    }

    return list;
  }
}
