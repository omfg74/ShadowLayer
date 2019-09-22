package com.example.shadowlayer

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shadowLayout = ShadowLayout(this)
        setContentView(shadowLayout)
        addOrangeButton(shadowLayout)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun addOrangeButton(shadowLayout: ShadowLayout) {
        val density = resources.displayMetrics.density
        val layoutParams = ViewGroup.MarginLayoutParams((72 * density).toInt(), (32 * density).toInt())
        val button1 = TextView(this)
        button1.gravity = Gravity.CENTER

        layoutParams.setMargins(dp(10), dp(10), 0, 0)
        button1.layoutParams = layoutParams
        button1.text = "Text"
        button1.setTextColor(Color.WHITE)
        button1.textSize = sp(6).toFloat()
        button1.setBackgroundColor(Color.parseColor("#FF4200"))
        button1.background = ContextCompat.getDrawable(this, R.drawable.round_corners_ornge)
        button1.layoutParams = layoutParams
        val shadow = Shadow()
        shadow.alpha = 0.9f
        shadow.radius = 20
        shadow.color = "#33ff003f"
        //        shadow.setColor("#64424242");
        //        shadow.setColor("#96424242");
        shadow.x = 0
        shadow.y = 10
        button1.setTag(R.id.shadow, shadow)
        shadowLayout.addView(button1)
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
