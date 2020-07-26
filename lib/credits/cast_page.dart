import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/tmdb_api/cast.dart';

class CastDetailsPage extends StatefulWidget {
  final String title;
  final MovieCast cast;

  CastDetailsPage({Key key, this.title, this.cast})
      : assert(cast != null),
        super(key: key);

  @override
  _CastDetailsPageState createState() => _CastDetailsPageState();
}

class _CastDetailsPageState extends State<CastDetailsPage> {
  @override
  Widget build(BuildContext context) {
    final cast = widget.cast;

    final textTheme = Theme.of(context).textTheme;
    final titleWidget = Text(
      cast.name,
      maxLines: 4,
      style: textTheme.headline5,
      overflow: TextOverflow.ellipsis,
    );

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
          ],
        ),
      ),
    );
  }
}
