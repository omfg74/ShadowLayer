package com.example.shadowlayer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.graphics.ColorUtils
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

class ShadowLayout : FrameLayout {
    internal var paint = Paint()
    val view = View(context)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, paint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        val width = child.width
        val height = child.height

        val shadow = child.getTag(R.id.shadow)
        if (shadow is Shadow) {
            val color = ColorUtils.setAlphaComponent(
                Color.parseColor(shadow.color),
                (shadow.alpha * 255).roundToInt()
            )
            paint.color = Color.parseColor(shadow.color)
            paint.setShadowLayer(
                shadow.radius.toFloat(),
                shadow.x.toFloat(),
                shadow.y.toFloat(),
                color
            )
            val k = ((4f / shadow.radius)) + 2
            val x = abs(shadow.x)
            val y = abs(shadow.y)
            val extraSize = min(50f, k * shadow.radius).dpInt
            val input = Bitmap.createBitmap(
                ((width + x.dp) + extraSize),
                ((height + y.dp) + extraSize), Bitmap.Config.ARGB_8888
            )
            val canvasBitmap = Canvas(input)
            canvasBitmap.save()
            canvasBitmap.translate(
                (x.dp.toFloat()) + shadow.radius.dp,
                y.dp.toFloat() + shadow.radius.dp
            )
            canvasBitmap.drawRoundRect(
                0f,
                0f,
                child.width.toFloat(),
                height.toFloat(),
                dp(shadow.cornerRadius.toInt()).toFloat(),
                dp(shadow.cornerRadius.toInt()).toFloat(),
                paint
            )
            canvasBitmap.restore()
            canvas.drawBitmap(
                input,
                child.x - x.dp - shadow.radius.dp,
                child.y - y.dp - shadow.radius.dp,
                paint
            )

        }
        return super.drawChild(canvas, child, drawingTime)

    }

    private fun dp(px: Int): Int {
        val density = resources.displayMetrics.density
        return (px * density).toInt()
    }

    private fun sp(px: Int): Int {
        val density = resources.displayMetrics.scaledDensity
        return (px * density).toInt()
    }

}