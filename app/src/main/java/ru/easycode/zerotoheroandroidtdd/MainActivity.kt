package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.contains

class MainActivity : AppCompatActivity() {

    private var linearLayout: LinearLayout? = null
    private var textView: TextView? = null
    private var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.titleTextView)
        button = findViewById(R.id.removeButton)
        linearLayout = findViewById(R.id.rootLayout)
        button?.setOnClickListener {
            linearLayout?.removeView(textView)
            button?.isEnabled = false
        }
        savedInstanceState?.let {
            if (!it.getBoolean(TEXT_VIEW_EXIST)) {
                linearLayout?.removeView(textView)
            }
            if (!it.getBoolean(BUTTON_ENABLED)) {
                button?.isEnabled = false
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        linearLayout?.let { linearLayoutLocal ->
            textView?.let { textViewLocal ->
                outState.putBoolean(TEXT_VIEW_EXIST, linearLayoutLocal.contains(textViewLocal))
            }
        }
        button?.let {
            outState.putBoolean(BUTTON_ENABLED, it.isEnabled)
        }
    }

    companion object {
        const val TEXT_VIEW_EXIST = "text_view_exist_key"
        const val BUTTON_ENABLED = "button_enabled_key"
    }
}