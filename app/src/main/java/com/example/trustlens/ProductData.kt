package com.example.trustlens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ProductData(
    val rank: Int,
    @DrawableRes val rankBgRes: Int,
    val rankTextColor: Int,
    @StringRes val nameRes: Int,
    val sales: Int,
    val rating: Float,
    val imageUrl: String
)