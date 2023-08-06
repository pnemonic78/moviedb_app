import 'package:flutter/widgets.dart';
import 'package:tmdb/parallax/parallax_delegate.dart';

/// https://docs.flutter.dev/cookbook/effects/parallax-scrolling

class ParallaxImage extends StatelessWidget {
  final Widget child;

  const ParallaxImage({super.key, required this.child});

  @override
  Widget build(BuildContext context) {
    return Flow(
      delegate: ParallaxFlowDelegate(
        scrollable: Scrollable.of(context),
        listItemContext: context,
      ),
      children: [child],
    );
  }
}
