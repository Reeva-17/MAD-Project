package com.example.trustlens.ui.theme

import com.example.trustlens.PerformanceMetricData
import com.example.trustlens.R



import android.animation.ValueAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView


class PerformanceMetricViewHolder(private val root: View) {
    private val label: TextView = root.findViewById(R.id.tv_perf_label)
    private val values: TextView = root.findViewById(R.id.tv_perf_values)
    private val fill: View = root.findViewById(R.id.view_progress_fill)

    fun bind(data: PerformanceMetricData) {
        label.setText(data.labelRes)
        values.text = root.context.getString(R.string.perf_value_format, data.current, data.target)
        fill.setBackgroundResource(data.fillDrawable)
        val percentage = data.current.toFloat() / data.target.toFloat()
        fill.post {
            val maxWidth = (fill.parent as? View)?.width?.toFloat() ?: 0f
            val targetWidth = (maxWidth * percentage).toInt()
            ValueAnimator.ofInt(0, targetWidth).apply {
                duration = 1000L
                interpolator = DecelerateInterpolator()
                addUpdateListener { animator ->
                    val lp = fill.layoutParams
                    lp.width = animator.animatedValue as Int
                    fill.layoutParams = lp
                }
                start()
            }
        }
    }
}