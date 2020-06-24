import 'dart:convert';
import 'dart:io';

import 'package:cached_network_image/cached_network_image.dart';
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
  static const api_url = "https://api.themoviedb.org/3/";
  static const image_url = "https://image.tmdb.org/t/p/%s%s";
  static const youtube_url = "https://www.youtube.com/watch?v=%s";
  static const youtube_thumbnail = "https://img.youtube.com/vi/%s/0.jpg";

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

  /// We use the `dart:io` HttpClient. More details: https://flutter.io/networking/
  // We specify the type here for readability. Since we're defining a final
  // field, the type is determined at initialization.
  final HttpClient _httpClient = HttpClient();

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

  static Future<Image> generateVideoThumbnail(
      Video video, double width, double height) async {
    if ((video == null) || (width <= 0) || (height <= 0)) {
      return null;
    }

    if (video.site == "YouTube") {
      return _generateYouTubeThumbnail(video.key, width, height);
    }

    var videoUrl;
    if (videoUrl != null) {
      final temp = await getTemporaryDirectory();
      final path = await VideoThumbnail.thumbnailFile(
        video: videoUrl,
        thumbnailPath: temp.path,
        imageFormat: ImageFormat.JPEG,
        maxHeight: height.toInt(),
        quality: 75,
      );
      final file = File(path);
      return Image.file(file, width: width, height: height);
    }
    return null;
  }

  static Future<Image> _generateYouTubeThumbnail(
      String videoId, double width, double height) async {
    final url = sprintf(youtube_thumbnail, [videoId]);
    return Image(
        image: CachedNetworkImageProvider(url), width: width, height: height);
  }

  static String getVideoUrl(Video video) {
    if (video == null) {
      return null;
    }
    if (video.site == "YouTube") {
      return sprintf(youtube_url, [video.key]);
    }
    return null;
  }

  /// Fetches and decodes a JSON object represented as a Dart [Map].
  ///
  /// Returns null if the API server is down, or the response is not JSON.
  Future<Map<String, dynamic>> _getJson(Uri uri) async {
    try {
      final httpRequest = await _httpClient.getUrl(uri);
      final httpResponse = await httpRequest.close();
      if (httpResponse.statusCode != HttpStatus.ok) {
        return null;
      }
      // The response is sent as a Stream of bytes that we need to convert to a
      // `String`.
      final responseBody = await httpResponse.transform(utf8.decoder).join();
      // Finally, the string is parsed into a JSON object.
      return json.decode(responseBody);
    } on Exception catch (e) {
      print(e);
      return null;
    }
  }
}
