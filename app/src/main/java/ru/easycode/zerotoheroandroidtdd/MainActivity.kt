package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var linearLayout: LinearLayout? = null
    private var textView: TextView? = null
    private var button: Button? = null
    private val count = Count.Base(2, 4)
    private var state: UiState = UiState.Base("0")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)
        linearLayout = findViewById(R.id.rootLayout)
        button?.setOnClickListener {
            button?.let { buttonLocal ->
                textView?.let { textViewLocal ->
                    state = count.increment(textViewLocal.text.toString())
                    state.subscribeState(button, textView)
                }
            }
        }
        savedInstanceState?.let {
            state = it.getSerializable(STATE_KEY) as UiState
            state.subscribeState(button, textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(STATE_KEY, state)
    }

    companion object {
        const val STATE_KEY = "state_key"
    }
}

interface Count {

    fun increment(number: String): UiState
    class Base(private val step: Int, private val max: Int) : Count {

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            if (max <= 0) throw IllegalStateException("max should be positive, but was $max")
            if (max < step) throw IllegalStateException("max should be more than step")
        }

        override fun increment(number: String): UiState {
            return if ((step + number.toInt() < max) && (step + number.toInt() + step <= max)) {
                UiState.Base((step + number.toInt()).toString())
            } else {
                UiState.Max((step + number.toInt()).toString())
            }
        }
    }
}

sealed class UiState : Serializable {
    abstract fun subscribeState(button: Button?, textView: TextView?)

    data class Base(private val text: String) : UiState() {
        override fun subscribeState(button: Button?, textView: TextView?) {
            textView?.text = text
        }

    }
    data class Max(private val text: String) : UiState() {
        override fun subscribeState(button: Button?, textView: TextView?) {
            textView?.text = text
            button?.isEnabled = false
        }
    }
}