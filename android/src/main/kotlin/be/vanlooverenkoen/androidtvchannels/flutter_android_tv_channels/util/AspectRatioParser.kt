package be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.util

import androidx.tvprovider.media.tv.TvContractCompat.PreviewProgramColumns

object AspectRatioParser {

    fun getAspectRatio(value: String): Int {
        return when (value) {
            "ASPECT_RATIO_16_9" -> PreviewProgramColumns.ASPECT_RATIO_16_9
            "ASPECT_RATIO_3_2" -> PreviewProgramColumns.ASPECT_RATIO_3_2
            "ASPECT_RATIO_4_3" -> PreviewProgramColumns.ASPECT_RATIO_4_3
            "ASPECT_RATIO_1_1" -> PreviewProgramColumns.ASPECT_RATIO_1_1
            "ASPECT_RATIO_2_3" -> PreviewProgramColumns.ASPECT_RATIO_2_3
            "UNKNOWN" -> -1
            else -> -1
        }
    }
}