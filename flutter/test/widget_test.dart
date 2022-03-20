// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:tmdb/di/app_injector.dart';
import 'package:tmdb/di/app_injector_module.dart';
import 'package:tmdb/di/injector_inherited.dart';

void main() {
  testWidgets('Movie categories smoke test', (WidgetTester tester) async {
    // Build our app and trigger a frame.
    final container = await AppInjector.create(new AppInjectorModule());
    final app = new InjectorWidget(
      child: container.app,
      api: container.api,
    );
    await tester.pumpWidget(app);

    // Verify that our main page is loaded.
    expect(find.text('Popular Movies'), findsOneWidget);
    expect(find.text('Movies Now Playing'), findsOneWidget);
    expect(find.text('No Movies'), findsNothing);

    // Tap the title bar and trigger a navigation.
    await tester.tap(find.text('Popular Movies'));
    await tester.pumpAndSettle();

    // Verify that page has shown.
    expect(find.text('Popular Movies'), findsOneWidget);
    expect(find.text('Movies Now Playing'), findsNothing);
  });
}
