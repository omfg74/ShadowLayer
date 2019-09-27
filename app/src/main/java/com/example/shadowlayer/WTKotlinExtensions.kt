package com.example.shadowlayer

import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt


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