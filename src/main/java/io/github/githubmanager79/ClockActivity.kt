package io.github.githubmanager79

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class ClockActivity @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

  private val paint = Paint().apply {
    style = Paint.Style.STROKE
    strokeWidth = 5f
    isAntiAlias = true
  }

  init {
    // Обновляем каждую секунду
    post(object : Runnable {
      override fun run() {
        invalidate()
        postDelayed(this, 1000)
      }
    })
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    val centerX = width / 2f
    val centerY = height / 2f

    // Рисуем циферблат
    paint.color = Color.BLACK
    canvas?.drawCircle(centerX, centerY, width / 2f - 10f, paint)

    val calendar = Calendar.getInstance()
    val hours = calendar.get(Calendar.HOUR)
    val minutes = calendar.get(Calendar.MINUTE)
    val seconds = calendar.get(Calendar.SECOND)

    // Рисуем стрелку часов
    val hourAngle = Math.toRadians((360 * (hours + minutes / 60.0) / 12).toDouble())
    canvas?.drawLine(centerX, centerY,
      centerX + (centerX / 2) * Math.sin(hourAngle).toFloat(),
      centerY - (centerY / 2) * Math.cos(hourAngle).toFloat(),
      paint)

    // Рисуем стрелку минут
    paint.color = Color.DKGRAY
    val minuteAngle = Math.toRadians((360 * minutes / 60).toDouble())
    canvas?.drawLine(centerX, centerY,
      centerX + (centerX - 20) * Math.sin(minuteAngle).toFloat(),
      centerY - (centerY - 20) * Math.cos(minuteAngle).toFloat(),
      paint)

    // Рисуем стрелку секунд
    paint.color = Color.RED
    val secondAngle = Math.toRadians((360 * seconds / 60).toDouble())
    canvas?.drawLine(centerX, centerY,
      centerX + (centerX - 10) * Math.sin(secondAngle).toFloat(),
      centerY - (centerY - 10) * Math.cos(secondAngle).toFloat(),
      paint)
  }
}
