package be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.builder

import android.content.ContentUris
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.net.Uri
import androidx.core.content.res.ResourcesCompat
import androidx.tvprovider.media.tv.Channel
import androidx.tvprovider.media.tv.ChannelLogoUtils.storeChannelLogo
import androidx.tvprovider.media.tv.PreviewProgram
import androidx.tvprovider.media.tv.TvContractCompat
import be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.model.MovieData
import be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.util.ResourceUtil

object ChannelBuilder {
    private const val DEFAULT_RESOURCE_NAME = "android_tv_channel_icon"

    fun addChannel(context: Context, name: String, iconResName: String?): Long {
        val packageName = context.packageName
        val channel = Channel.Builder().setType(TvContractCompat.Channels.TYPE_PREVIEW)
                .setDisplayName(name)
                .setAppLinkIntentUri(Uri.parse("$packageName.androidtvchannels://open_via_launcher"))
                .build()
        val channelUri = context.contentResolver.insert(TvContractCompat.Channels.CONTENT_URI, channel.toContentValues()) ?: throw NullPointerException("channelUri is null")
        val channelId = ContentUris.parseId(channelUri)
        TvContractCompat.requestChannelBrowsable(context, channelId)

        when {
            iconResName != null -> storeChannelLogo(context, channelId, ResourceUtil.getBitmap(context, iconResName))
            ResourceUtil.containsResource(context, DEFAULT_RESOURCE_NAME) -> storeChannelLogo(context, channelId, ResourceUtil.getBitmap(context, DEFAULT_RESOURCE_NAME))
            else -> storeChannelLogo(context, channelId, Uri.parse("android.resource://$packageName/mipmap/ic_launcher"))
        }
        return channelId
    }

    fun deleteChannel(context: Context, channelId: Long) {
        context.contentResolver.delete(TvContractCompat.buildChannelUri(channelId), null, null)
    }

    fun addMovieContent(
            context: Context,
            channelId: Long,
            movieData: MovieData
    ): Long {
        val builder = PreviewProgram.Builder()
        builder.setChannelId(channelId)
                .setType(TvContractCompat.PreviewPrograms.TYPE_MOVIE)
                .setTitle(movieData.title)
                .setGenre(movieData.genre)
        movieData.durationMillis?.let { builder.setDurationMillis(it) }
        movieData.releaseDate?.let { builder.setReleaseDate(it) }
        builder.setDescription(movieData.description)
        movieData.posterUri?.let { builder.setPosterArtUri(Uri.parse(it)) }
        builder.setIntentUri(Uri.parse(movieData.intentUri))
        val contentId = context.contentResolver.insert(TvContractCompat.PreviewPrograms.CONTENT_URI, builder.build().toContentValues())
                ?: throw NullPointerException("contentUri is null")
        return ContentUris.parseId(contentId)
    }

    fun deleteContent(context: Context, contentId: Long) {
        context.contentResolver.delete(TvContractCompat.buildPreviewProgramUri(contentId), null, null)
    }
}