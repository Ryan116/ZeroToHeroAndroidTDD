package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var actionButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionButton = findViewById(R.id.actionButton)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.titleTextView)
        actionButton.setOnClickListener {
            actionButton.isEnabled = false
            progressBar.isVisible = true
            java.util.Timer().schedule(object : TimerTask() {
                override fun run() = runOnUiThread {
                    progressBar.isVisible = false
                    actionButton.isEnabled = true
                    textView.isVisible = true
                }
            }, 2000)
        }
    }
}