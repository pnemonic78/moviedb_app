import 'dart:math';

import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/video.dart';

class VideoTile extends StatelessWidget {
  final Video video;
  final ValueChanged<Video> onTap;

  const VideoTile({
    Key key,
    @required this.video,
    @required this.onTap,
  })  : assert(video != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final imageWidth = thumbnailWidth;
    final imageHeight = thumbnailHeight;

    return FutureBuilder<Widget>(
      future: TMDBApi.generateVideoThumbnail(
        video,
        thumbnailWidth,
        thumbnailHeight,
      ),
      builder: (BuildContext context, AsyncSnapshot<Widget> snapshot) {
        Widget content;
        Function onVideoTap;

        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasData) {
            final thumbnail = snapshot.data;

            content = Stack(
              alignment: Alignment.center,
              children: <Widget>[
                thumbnail,
                Icon(
                  Icons.play_circle_outline,
                  size: min(imageHeight, imageWidth) / 2,
                ),
              ],
            );
            onVideoTap = onTap == null ? null : () => onTap(video);
          } else {
            content = Icon(
              Icons.error_outline,
              size: min(imageWidth, imageHeight),
            );
          }
        } else {
          content = Center(
            child: Container(
              child: CircularProgressIndicator(),
              width: imageWidth,
              height: imageHeight,
            ),
          );
        }

        final textTheme = Theme.of(context).textTheme;

        final titleWidget = Text(
          video.name,
          maxLines: 3,
          style: textTheme.caption,
          overflow: TextOverflow.ellipsis,
        );

        return Card(
          child: InkWell(
            onTap: onVideoTap,
            child: Padding(
              padding: paddingAll_8,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  content,
                  Expanded(
                    child: Padding(
                      padding: paddingLeft_8,
                      child: titleWidget,
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}
