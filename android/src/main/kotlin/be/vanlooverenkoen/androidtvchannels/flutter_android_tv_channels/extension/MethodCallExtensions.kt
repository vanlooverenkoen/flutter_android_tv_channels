package be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.extension

import io.flutter.plugin.common.MethodCall

fun MethodCall.argumentsLong(key: String): Long? {
    try {
        return argument<Long>(key)
    } catch (e: Exception) {
    }
    val result = argument<Int>(key)
    return result?.toLong()
}