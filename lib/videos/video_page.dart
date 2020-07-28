import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:video_player/video_player.dart';
import 'package:youtube_player_flutter/youtube_player_flutter.dart';

class VideoPlayerPage extends StatefulWidget {
  final String title;
  final MovieVideo video;

  VideoPlayerPage({Key key, this.title, this.video})
      : assert(video != null),
        super(key: key);

  @override
  _VideoPlayerPageState createState() => _VideoPlayerPageState();
}

class _VideoPlayerPageState extends State<VideoPlayerPage> {
  VideoPlayerController _controller;
  YoutubePlayerController _youtubeController;

  @override
  void initState() {
    super.initState();

    if (widget.video.site == "YouTube") {
      _youtubeController = YoutubePlayerController(
        initialVideoId: widget.video.key,
        flags: YoutubePlayerFlags(
          mute: false,
          autoPlay: true,
          loop: false,
        ),
      )..addListener(() {
          setState(() {});
        });
    } else {
      final url = TMDBApi.getVideoUrl(widget.video);
      _controller = VideoPlayerController.network(url)
        ..addListener(() {
          setState(() {});
        })
        ..initialize()
        ..play();
    }
  }

  @override
  void dispose() {
    _controller?.pause();
    _controller?.dispose();
    _youtubeController?.pause();
    _youtubeController?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    final titleWidget = Text(
      widget.video.name,
      maxLines: 4,
      style: textTheme.headline5,
      overflow: TextOverflow.ellipsis,
    );

    Widget player;
    if (_youtubeController != null) {
      player = YoutubePlayer(
        controller: _youtubeController,
        showVideoProgressIndicator: true,
      );
    } else {
      player = AspectRatio(
        aspectRatio: _controller.value.aspectRatio,
        child: Stack(
          alignment: Alignment.bottomCenter,
          children: <Widget>[
            VideoPlayer(_controller),
            VideoProgressIndicator(_controller),
          ],
        ),
      );
    }

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Padding(
        padding: paddingAll_8,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            titleWidget,
            Expanded(
              child: Center(child: player),
            ),
          ],
        ),
      ),
    );
  }
}
