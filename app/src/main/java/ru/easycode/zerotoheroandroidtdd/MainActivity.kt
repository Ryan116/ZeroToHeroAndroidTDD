package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.contains

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    private var rootLayout: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.removeButton)
        textView = findViewById(R.id.titleTextView)
        rootLayout = findViewById<LinearLayout>(R.id.rootLayout)
        button.setOnClickListener {
            rootLayout?.removeView(textView)
        }
        savedInstanceState?.let {
            if (!it.getBoolean(TEXT_VIEW_EXIST_KEY)) {
                rootLayout?.removeView(textView)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        rootLayout?.let { rootLayoutLocal ->
            textView?.let { textViewLocal ->
                outState.putBoolean(TEXT_VIEW_EXIST_KEY, rootLayoutLocal.contains(textViewLocal))
            }
        }
    }

    companion object {
        const val TEXT_VIEW_EXIST_KEY = "text_view_exist"
    }
}