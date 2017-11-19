package com.gitlab.drzepka.superconverter

import android.content.res.Resources
import android.util.DisplayMetrics

object Utils {

    private val dpiRatio: Float by lazy {
        val mertics = Resources.getSystem().displayMetrics
        mertics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT
    }

    /**
     * Kowertuje DP -> PX.
     */
    fun dpToPx(dp: Float): Float = dp * dpiRatio

    /**
     * Kowertuje PX -> DP
     */
    fun pxToDp(px: Float): Float = px / dpiRatio
}