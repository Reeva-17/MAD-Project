package com.example.trustlens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnalyzeActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    private lateinit var step1: TextView
    private lateinit var step2: TextView
    private lateinit var step3: TextView
    private lateinit var step4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze)

        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progressText)

        step1 = findViewById(R.id.step1)
        step2 = findViewById(R.id.step2)
        step3 = findViewById(R.id.step3)
        step4 = findViewById(R.id.step4)

        startAnalysis()
    }

    private fun startAnalysis() {
        val handler = Handler(Looper.getMainLooper())

        for (i in 0..100 step 5) {
            handler.postDelayed({
                progressBar.progress = i
                progressText.text = "$i%"

                when (i) {
                    25 -> step2.text = "✔ Scanning Reviews"
                    50 -> step3.text = "✔ Detecting Patterns"
                    75 -> step4.text = "✔ Calculating Trust Score"
                }

                if (i == 100) {
                    // TODO: Move to result screen
                }

            }, (i * 100).toLong())
        }
    }
}
