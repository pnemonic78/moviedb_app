import 'package:flutter/material.dart';

Size textSize(String text, TextStyle style, int maxLines) {
  final TextPainter textPainter = TextPainter(
      text: TextSpan(text: text, style: style),
      maxLines: maxLines,
      textDirection: TextDirection.ltr)
    ..layout();
  return textPainter.size;
}
