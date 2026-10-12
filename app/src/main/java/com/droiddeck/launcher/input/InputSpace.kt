package com.droiddeck.launcher.input

import android.graphics.RectF

object InputSpace {
    const val WIDTH = 1920
    const val HEIGHT = 1080

    /**
     * Maps a view-coordinate [position] within the letterboxed picture [rect] to
     * normalized scene space [0..WIDTH-1].
     */
    fun x(position: Float, rect: RectF): Int =
        scale(position - rect.left, rect.width(), WIDTH)

    fun y(position: Float, rect: RectF): Int =
        scale(position - rect.top, rect.height(), HEIGHT)

    /** Overloads with scalar [offset] and [drawnSize] (used by unit tests without mocking RectF). */
    fun x(position: Float, offset: Float, drawnSize: Float): Int =
        scale(position - offset, drawnSize, WIDTH)

    fun y(position: Float, offset: Float, drawnSize: Float): Int =
        scale(position - offset, drawnSize, HEIGHT)

    /** Backward-compatible overloads for view-extent scaling. */
    fun x(position: Float, viewWidth: Int): Int = scale(position, viewWidth.toFloat(), WIDTH)

    fun y(position: Float, viewHeight: Int): Int = scale(position, viewHeight.toFloat(), HEIGHT)

    private fun scale(position: Float, extent: Float, size: Int): Int =
        if (extent <= 0f) 0 else (position / extent * size).toInt().coerceIn(0, size - 1)
}
