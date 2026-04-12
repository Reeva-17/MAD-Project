package com.example.trustlens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PerformanceMetricData(
    @StringRes val labelRes: Int,
    val current: Int,
    val target: Int,
    @DrawableRes val fillDrawable: Int
)