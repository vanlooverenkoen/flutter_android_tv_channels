import 'package:android_tv_channels/src/model/bridge/aspect_ratio.dart';

class PosterAspectRatioParser {
  PosterAspectRatioParser._();

  static String? getAspectRatio(PosterAspectRatio? aspectRatio) {
    if (aspectRatio == null) return null;
    switch (aspectRatio) {
      case PosterAspectRatio.ASPECT_RATIO_16_9:
        return 'ASPECT_RATIO_16_9';
      case PosterAspectRatio.ASPECT_RATIO_3_2:
        return 'ASPECT_RATIO_3_2';
      case PosterAspectRatio.ASPECT_RATIO_4_3:
        return 'ASPECT_RATIO_4_3';
      case PosterAspectRatio.ASPECT_RATIO_1_1:
        return 'ASPECT_RATIO_1_1';
      case PosterAspectRatio.ASPECT_RATIO_2_3:
        return 'ASPECT_RATIO_2_3';
      case PosterAspectRatio.ASPECT_RATIO_MOVIE_POSTER:
        return 'ASPECT_RATIO_MOVIE_POSTER';
      case PosterAspectRatio.UNKNOWN:
        return 'UNKNOWN';
    }
  }
}
