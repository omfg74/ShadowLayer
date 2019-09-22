package com.example.shadowlayer

import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt

/**
 * Created: Belozerov Sergei
 * E-mail: belozerow@gmail.com
 * Company: APPGRANULA LLC
 * Date: 02/10/2018
 */
inline fun View.waitForLayout(crossinline f: () -> Unit) = with(viewTreeObserver) {
    addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            f()
        }
    })
}

inline fun View.waitForLayoutPreDraw(crossinline f: () -> Unit) = with(viewTreeObserver) {
    addOnPreDrawListener (object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            f()
            return true
        }
    })
}
val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.px: Float
    get() = (this / Resources.getSystem().displayMetrics.density)
val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

val Double.px: Double
    get() = (this / Resources.getSystem().displayMetrics.density)
val Double.dp: Double
    get() = (this * Resources.getSystem().displayMetrics.density)

val Double.dpInt: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

val Float.dpInt: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()