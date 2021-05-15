import 'dart:async';

import 'package:flutter/services.dart';

class FlutterAndroidTvChannels {
  static const MethodChannel _channel = MethodChannel('flutter_android_tv_channels');

  static Future<String?> get platformVersion async {
    final version = await _channel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
