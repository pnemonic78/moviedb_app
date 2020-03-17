import 'dart:io';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/tmdb_api/movie_details.dart';
import 'package:tmdb/tmdb_api/now_playing_response.dart';
import 'package:tmdb/tmdb_api/video.dart';
import 'package:tmdb/tmdb_api/videos_response.dart';
import 'package:video_thumbnail/video_thumbnail.dart';

import 'movie.dart';
import 'movies.dart';
import 'now_playing_response.dart';

class TMDBApi {
  static const image_url = "https://image.tmdb.org/t/p/%s%s";
  static const youtube_url = "https://www.youtube.com/watch?v=%s";

  static const _original = "original";
  static const backdrop_sizes = [
    "w300",
    "w780",
    "w1280",
    _original,
  ];
  static const logo_sizes = [
    "w45",
    "w92",
    "w154",
    "w185",
    "w300",
    "w500",
    _original,
  ];
  static const poster_sizes = [
    "w92",
    "w154",
    "w185",
    "w342",
    "w500",
    "w780",
    _original,
  ];
  static const profile_sizes = [
    "w45",
    "w185",
    "h632",
    _original,
  ];
  static const still_sizes = [
    "w92",
    "w185",
    "w300",
    _original,
  ];

  static String generatePosterUrl(String path, double width, double height) {
    if ((path == null) || (width <= 0) || (height <= 0)) {
      return null;
    }
    final size = findSize(width, height, poster_sizes);

    return sprintf(image_url, [size, path]);
  }

  static String findSize(double width, double height, List<String> sizes) {
    String result = _original;
    double minDelta = double.infinity;
    double delta;

    for (var size in sizes) {
      if (size.startsWith("w")) {
        final posterWidth = double.parse(size.substring(1));
        delta = (width - posterWidth).abs();
        if (delta < minDelta) {
          result = size;
        }
      } else if (size.startsWith("h")) {
        final posterHeight = double.parse(size.substring(1));
        delta = (height - posterHeight).abs();
        if (delta < minDelta) {
          result = size;
        }
      }
    }

    return result;
  }

  Future<MoviesNowPlayingResponse> getNowPlaying(BuildContext context) async {
    return Movies.getNowPlaying(context);
  }

  Future<MovieDetails> getMovieDetails(
      BuildContext context, Movie movie) async {
    MovieDetails movieDetails = await Movies.getMovieDetails(context, movie.id);
    return movieDetails ?? MovieDetails.of(movie);
  }

  Future<VideosResponse> getMovieVideos(
      BuildContext context, Movie movie) async {
    return Movies.getMovieVideos(context, movie.id);
  }

  static Future<File> generateVideoThumbnailFile(
      Video video, int width, int height) async {
    if ((video == null) || (width <= 0) || (height <= 0)) {
      return null;
    }

    var videoUrl;
    if (video.site == "YouTube") {
      videoUrl = sprintf(youtube_url, [video.key]);
    }

    if (videoUrl != null) {
      await Future.delayed(Duration(seconds: 5));//±!@
      final path = await VideoThumbnail.thumbnailFile(
        video: videoUrl,
        thumbnailPath: (await getTemporaryDirectory()).path,
        imageFormat: ImageFormat.JPEG,
        maxHeight: height,
        quality: 75,
      );
      return File(path);
    }
    return null;
  }
}
