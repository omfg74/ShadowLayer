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
//
//
////            paint.setColor(Color.parseColor("#33ff003f"))
//            paint.setShadowLayer(20f, 0f, 10f, Color.parseColor("#33ff003f"))
//            val view = View(context)
//            view.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
//            val input = Bitmap.createBitmap(((width?.plus(shadow.x) ?: 0) + dp(2*shadow.radius)).toInt(),((height?.plus(shadow.y) ?: 0) + dp(2*shadow.radius)).toInt(),Bitmap.Config.ARGB_8888)
//            val canvasBitmap = Canvas(input)
//            canvasBitmap.save()
//            canvasBitmap.translate(( child.x), ( child.y))
////            child.draw(canvasBitmap)
//            canvasBitmap.drawRect(0f,0f,child.width.toFloat(),child.height.toFloat(),paint)
//            canvasBitmap.restore()
////            canvasBitmap.drawBitmap(input,0f,0f, paint)
//            canvas?.drawBitmap(input,0f,0f, paint)
            val color = ColorUtils.setAlphaComponent(Color.parseColor(shadow.color), (shadow.alpha * 255).roundToInt())
            paint.color = Color.parseColor(shadow.color)
            paint.setShadowLayer(shadow.radius.toFloat(), shadow.x.toFloat(), shadow.y.toFloat(), color)
            var k = 0f
            k = ((4f / shadow.radius)) + 2
            val x = abs(shadow.x)
            val y = abs(shadow.y)
            val extraSize = min(50f, k * shadow.radius).dpInt
            val input = Bitmap.createBitmap(
                ((width + x.dp) + extraSize),
                ((height + y.dp) + extraSize), Bitmap.Config.ARGB_8888)
            val canvasBitmap = Canvas(input)
            canvasBitmap.save()
            canvasBitmap.translate((x.dp.toFloat()), (y.dp.toFloat()))
            canvasBitmap.drawRoundRect(0f, 0f, child.width.toFloat(), height.toFloat(), dp(shadow.cornerRadius.toInt()).toFloat(), dp(shadow.cornerRadius.toInt()).toFloat(), paint)
            canvasBitmap.restore()
            canvas.drawBitmap(input, child.x - x.dp, child.y - y.dp, paint)



        }
        return super.drawChild(canvas, child, drawingTime)
//        return false
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