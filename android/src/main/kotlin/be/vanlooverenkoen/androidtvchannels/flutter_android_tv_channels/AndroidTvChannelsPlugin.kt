package be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels

import android.content.Context
import be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.builder.ChannelBuilder
import be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.extension.argumentsLong
import be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.model.MovieDataParser
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class AndroidTvChannelsPlugin : FlutterPlugin, MethodCallHandler {

    private lateinit var channel: MethodChannel
    private lateinit var applicationContext: Context

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "android_tv_channels")
        applicationContext = flutterPluginBinding.applicationContext
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        try {
            when (call.method) {
                "addChannel" -> addChannel(call, result)
                "deleteChannel" -> deleteChannel(call, result)
                "addMovies" -> addMovies(call, result)
                "deleteContent" -> deleteContent(call, result)
                else -> result.notImplemented()
            }
        } catch (e: Exception) {
            result.error("500", "Something went wrong: ${e.message}", e)
        }
    }

    private fun addChannel(call: MethodCall, result: Result) {
        val channelName = call.argument<String>("channelName")
        val iconResName = call.argument<String>("iconResName")
        if (channelName == null) {
            result.error("400", "channel_name is required", null)
            return
        }
        val channelId = ChannelBuilder.addChannel(applicationContext, channelName, iconResName)
        result.success(channelId)
    }

    private fun deleteChannel(call: MethodCall, result: Result) {
        val channelId = call.argumentsLong("channelId")
        if (channelId == null) {
            result.error("400", "channel_id is required", null)
            return
        }
        ChannelBuilder.deleteChannel(applicationContext, channelId)
        result.success(true)
    }

    private fun addMovies(call: MethodCall, result: Result) {
        val channelId = call.argumentsLong("channelId")
        if (channelId == null) {
            result.error("400", "channel_id is required", null)
            return
        }
        val moviesData = call.argument<List<Map<String, Any>>>("movies")
        if (moviesData == null) {
            result.error("400", "movies is required", null)
            return
        }
        val movies = MovieDataParser.parseMovies(moviesData)
        val ids = mutableListOf<Long>()
        movies.forEach {
            val contentId = ChannelBuilder.addMovieContent(applicationContext, channelId, it)
            ids.add(contentId)
        }
        result.success(ids)
    }

    private fun deleteContent(call: MethodCall, result: Result) {
        val contentId = call.argumentsLong("contentId")
        if (contentId == null) {
            result.error("400", "content_id is required", null)
            return
        }
        ChannelBuilder.deleteContent(applicationContext, contentId)
        result.success(true)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
