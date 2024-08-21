package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.changeButton)
        textView = findViewById(R.id.titleTextView)
        button.setOnClickListener {
            textView?.text = "I am an Android Developer!"
        }
        savedInstanceState?.let {
            textView?.text = it.getString(TEXT_STATE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE, textView?.text.toString())
    }



    companion object {
        const val TEXT_STATE = "text_state_key"
    }

}