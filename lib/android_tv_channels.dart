import 'dart:async';

import 'package:android_tv_channels/src/model/bridge/movie_data.dart';
import 'package:android_tv_channels/src/model/parser/movie_data_parser.dart';
import 'package:flutter/services.dart';

export 'src/model/bridge/movie_data.dart';

class AndroidTvChannels {
  static const MethodChannel _channel = MethodChannel('android_tv_channels');

  static Future<int> addChannel(
      {required String name, String? iconResName}) async {
    final result = await _channel.invokeMethod<int>('addChannel', {
      'channelName': name,
      'iconResName': iconResName,
    });
    if (result == null) throw ArgumentError('createChannel returned null');
    return result;
  }

  static Future<void> deleteChannel({required int channelId}) async {
    await _channel.invokeMethod<void>('deleteChannel', {
      'channelId': channelId,
    });
  }

  static Future<int> addMovie(
      {required int channelId, required MovieData movie}) async {
    final result = await addMovies(contentId: channelId, movies: [movie]);
    return result.first;
  }

  static Future<List<int>> addMovies(
      {required int contentId, required List<MovieData> movies}) async {
    final result = await _channel.invokeMethod<List<dynamic>>('addMovies', {
      'channelId': contentId,
      'movies': MovieDataParser.parseMovies(movies),
    });
    if (result == null) throw ArgumentError('addMovies returned null');
    return result.map((dynamic e) => e as int).toList(); // ignore: avoid_as
  }

  static Future<void> deleteContent({required int contentId}) async {
    await _channel.invokeMethod<void>('deleteContent', {
      'contentId': contentId,
    });
  }
}
