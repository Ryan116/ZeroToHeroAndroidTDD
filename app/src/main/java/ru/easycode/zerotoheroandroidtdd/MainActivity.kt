package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var linearLayout: LinearLayout? = null
    private var textView: TextView? = null
    private var button: Button? = null
    private val count = Count.Base(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)
        linearLayout = findViewById(R.id.rootLayout)
        button?.setOnClickListener {
            button?.let { buttonLocal ->
                textView?.let { textViewLocal ->
                    val result = count.increment(textViewLocal.text.toString())
                    textViewLocal.text = result
                }
            }
        }
    }
}

interface Count {

    fun increment(number: String): String
    class Base(step: Int) : Count {

        init {
            if (step <= 0) throw IllegalStateException( "step should be positive, but was $step")
        }

        private val number: Int = step
        override fun increment(value: String): String {
            return (this.number + value.toInt()).toString()
        }
    }
}