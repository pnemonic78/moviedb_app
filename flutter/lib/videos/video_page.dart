import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/video.dart';
import 'package:video_player/video_player.dart';
import 'package:youtube_player_flutter/youtube_player_flutter.dart';

class VideoPlayerPage extends StatefulWidget {
  final String title;
  final MovieVideo video;

  const VideoPlayerPage({super.key, required this.title, required this.video});

  @override
  State<VideoPlayerPage> createState() => _VideoPlayerPageState();
}

class _VideoPlayerPageState extends State<VideoPlayerPage> {
  VideoPlayerController? _controller;
  YoutubePlayerController? _youtubeController;

  @override
  void initState() {
    super.initState();

    if (widget.video.site == TMDBApi.siteYouTube) {
      _youtubeController = YoutubePlayerController(
        initialVideoId: widget.video.key,
        flags: const YoutubePlayerFlags(
          mute: false,
          autoPlay: true,
          loop: false,
        ),
      )..addListener(() {
          setState(() {});
        });
    } else {
      final url = TMDBApi.getVideoUrl(widget.video);
      if (url != null) {
        Uri uri = Uri.parse(url);
        _controller = VideoPlayerController.networkUrl(uri)
          ..addListener(() {
            setState(() {});
          })
          ..initialize()
          ..play();
      }
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
      style: textTheme.headlineSmall,
      overflow: TextOverflow.ellipsis,
    );

    Widget player;
    final youtubeController = _youtubeController;
    final controller = _controller;
    if (youtubeController != null) {
      player = YoutubePlayer(
        controller: youtubeController,
        showVideoProgressIndicator: true,
      );
    } else if (controller != null) {
      player = AspectRatio(
        aspectRatio: controller.value.aspectRatio,
        child: Stack(
          alignment: Alignment.bottomCenter,
          children: <Widget>[
            VideoPlayer(controller),
            VideoProgressIndicator(
              controller,
              allowScrubbing: true,
            ),
          ],
        ),
      );
    } else {
      player = const Center(
        child: Icon(
          Icons.error_outline,
          size: errorIconSize,
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
