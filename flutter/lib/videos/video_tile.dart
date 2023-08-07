import 'dart:math';

import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/video.dart';

class VideoTile extends StatelessWidget {
  final MovieVideo video;
  final ValueChanged<MovieVideo> onTap;

  const VideoTile({
    super.key,
    required this.video,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    const imageWidth = thumbnailWidth;
    const imageHeight = thumbnailHeight;

    return FutureBuilder<Widget?>(
      future: TMDBApi.generateVideoThumbnail(
        video,
        imageWidth,
        imageHeight,
      ),
      builder: (BuildContext context, AsyncSnapshot<Widget?> snapshot) {
        Widget content;
        GestureTapCallback? onVideoTap;

        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            final thumbnail = snapshot.data;

            if (thumbnail == null) {
              content = Icon(
                Icons.error_outline,
                size: min(imageWidth, imageHeight),
              );
            } else {
              content = Stack(
                alignment: Alignment.center,
                children: <Widget>[
                  thumbnail,
                  Icon(
                    Icons.play_circle_outline,
                    size: min(imageWidth, imageHeight) / 2,
                  ),
                ],
              );
              onVideoTap = () => onTap(video);
            }
          } else {
            content = Icon(
              Icons.error_outline,
              size: min(imageWidth, imageHeight),
            );
          }
        } else {
          content = const Center(
            child: SizedBox(
              width: imageWidth,
              height: imageHeight,
              child: CircularProgressIndicator(),
            ),
          );
        }

        final textTheme = Theme.of(context).textTheme;

        final titleWidget = Text(
          video.name,
          maxLines: 2,
          style: textTheme.bodySmall,
          overflow: TextOverflow.ellipsis,
        );

        return Card(
          child: InkWell(
            onTap: onVideoTap,
            child: Padding(
              padding: paddingAll_8,
              child: SizedBox(
                width: imageWidth,
                child: Column(
                  children: <Widget>[
                    content,
                    Padding(padding: paddingTop_8, child: titleWidget),
                  ],
                ),
              ),
            ),
          ),
        );
      },
    );
  }
}
