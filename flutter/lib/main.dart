import 'package:flutter/material.dart';
import 'package:tmdb/di/app_injector.dart';
import 'package:tmdb/di/app_injector_module.dart';
import 'package:tmdb/di/injector_inherited.dart';

void main() async {
  final container = await AppInjector.create(AppInjectorModule());
  final app = InjectorWidget(
      api: container.api,
      child: container.app,
  );
  runApp(app);
}
