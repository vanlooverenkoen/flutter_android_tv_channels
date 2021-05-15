package be.vanlooverenkoen.androidtvchannels.flutter_android_tv_channels.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import androidx.core.content.ContextCompat

object ResourceUtil {
    fun getResource(context: Context, resourceName: String): Int {
        return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }

    fun getBitmap(context: Context, resourceName: String): Bitmap {
        val res = getResource(context, resourceName)
        return when (val drawable = ContextCompat.getDrawable(context, res)) {
            is BitmapDrawable -> drawable.bitmap
            is VectorDrawable -> getBitmap(drawable)
            else -> throw IllegalArgumentException("unsupported drawable type")
        }
    }

    private fun getBitmap(vectorDrawable: VectorDrawable): Bitmap {
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        vectorDrawable.draw(canvas)
        return bitmap
    }

    fun containsResource(context: Context, resourceName: String): Boolean {
        val checkExistence: Int = getResource(context, resourceName);
        return checkExistence != 0;
    }
}