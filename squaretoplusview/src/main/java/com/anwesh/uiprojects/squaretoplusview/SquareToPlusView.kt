package com.anwesh.uiprojects.squaretoplusview

/**
 * Created by anweshmishra on 15/04/19.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 2
val parts : Int = 2
val scGap : Float = 0.5f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#673AB7")
val backColor : Int = Color.parseColor("#BDBDBD")
val angleDeg : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * scGap * dir
fun Int.sf() : Float = 1f - 2 * this

fun Canvas.drawRotatingLine(size : Float, sc1 : Float, sc2 : Float, paint : Paint) {
    save()
    translate(size * (1 - sc2), 0f)
    for (j in 0..(parts - 1)) {
        val scj : Float = sc1.divideScale(j, parts)
        save()
        rotate(angleDeg * scj * j.sf())
        drawLine(0f, 0f, 0f,-size * j.sf(), paint)
        restore()
    }
    restore()
}

fun Canvas.drawSTPNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    save()
    translate(w / 2, gap * (i + 1))
    for (j in 0..(lines - 1)) {
        save()
        rotate(90f * j)
        drawRotatingLine(size, sc1, sc2, paint)
        restore()
    }
    restore()
}

