package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.titleTextView)
        val button = findViewById<Button>(R.id.hideButton)
        button.setOnClickListener {
            textView?.isVisible = false
        }
        savedInstanceState?.let {
            textView?.isVisible = it.getBoolean(TEXT_VIEW_VISIBILITY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        textView?.let {
            outState.putBoolean(TEXT_VIEW_VISIBILITY, it.isVisible)
        }
    }

    companion object {
        const val TEXT_VIEW_VISIBILITY = "text_view_visibility_key"
    }
}