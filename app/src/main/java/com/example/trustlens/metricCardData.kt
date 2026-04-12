package com.example.trustlens


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MetricCardData(
    @DrawableRes val iconRes: Int,
    @DrawableRes val iconBgRes: Int,
    val value: String,
    @StringRes val labelRes: Int
)