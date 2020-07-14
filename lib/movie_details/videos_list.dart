import 'package:flutter/material.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';
import 'package:tmdb/tmdb_api/video.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';

import 'video_tile.dart';

class VideosList extends StatelessWidget {
  final MovieDetails movie;
  final ValueChanged<Video> onTap;
  final TMDBApi _api = TMDBApi();

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
            content = Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: _buildVideoList(context, snapshot.data.results));
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
    return _api.getMovieVideos(context, movie);
  }

  List<Widget> _buildVideoList(BuildContext context, List<Video> videos) {
    final textTheme = Theme.of(context).textTheme;
    final labelStyle = textTheme.subtitle1;
    final string = AppLocalizations.of(context);

    final videosLabel = Text(
      string.videos_label,
      style: labelStyle,
    );

    final list = <Widget>[];
    list.add(videosLabel);

    for (var video in videos) {
      list.add(VideoTile(
        video: video,
        onTap: onTap,
      ));
    }

    return list;
  }
}
