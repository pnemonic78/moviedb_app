import 'package:flutter/material.dart';
import 'package:tmdb/di/app_injector.dart';
import 'package:tmdb/di/app_injector_module.dart';

void main() async {
  final container = await AppInjector.create(new AppInjectorModule());
  runApp(container.app);
}
