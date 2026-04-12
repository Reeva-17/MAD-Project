package com.example.trustlens

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView


class MetricCardViewHolder(private val root: View) {
    private val iconBg: FrameLayout = root.findViewById(R.id.fl_icon_bg)
    private val icon: ImageView = root.findViewById(R.id.iv_metric_icon)
    private val value: TextView = root.findViewById(R.id.tv_metric_value)
    private val label: TextView = root.findViewById(R.id.tv_metric_label)

    fun bind(data: MetricCardData) {
        iconBg.setBackgroundResource(data.iconBgRes)
        icon.setImageResource(data.iconRes)
        value.text = data.value
        label.setText(data.labelRes)
    }
}