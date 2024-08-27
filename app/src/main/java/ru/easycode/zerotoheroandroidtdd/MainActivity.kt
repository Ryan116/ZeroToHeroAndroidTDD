package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var decrementButton: Button
    private lateinit var incrementButton: Button
    private lateinit var countTextView: TextView
    private val count = Count.Base(2, 4, 0)
    private lateinit var state: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        decrementButton = findViewById(R.id.decrementButton)
        incrementButton = findViewById(R.id.incrementButton)
        countTextView = findViewById(R.id.countTextView)
        incrementButton.setOnClickListener {
            state = count.increment(countTextView.text.toString())
            state.subscribeState(incrementButton, decrementButton, countTextView)
        }
        decrementButton.setOnClickListener {
            state = count.decrement(countTextView.text.toString())
            state.subscribeState(incrementButton, decrementButton, countTextView)
        }
        savedInstanceState?.let {
            state = it.getSerializable(STATE_KEY) as UiState
            state.subscribeState(incrementButton, decrementButton, countTextView)
        } ?.let {
            state = count.initial(countTextView.text.toString())
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

    fun decrement(number: String): UiState

    fun initial(number: String): UiState
    class Base(
        private val step: Int,
        private val max: Int,
        private val min: Int
    ) : Count {

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            if (max <= 0) throw IllegalStateException("max should be positive, but was $max")
            if (max < step) throw IllegalStateException("max should be more than step")
            if (max < min) throw IllegalStateException("max should be more than min")
        }

        override fun increment(number: String): UiState {
            return if ((step + number.toInt() < max) && (step + number.toInt() + step <= max)) {
                UiState.Base((step + number.toInt()).toString())
            } else {
                UiState.Max((step + number.toInt()).toString())
            }
        }

        override fun decrement(number: String): UiState {
            return if ((number.toInt() - step < max) && (number.toInt() - step - step >= min)) {
                UiState.Base((number.toInt() - step).toString())
            } else {
                UiState.Min((number.toInt() - step).toString())
            }
        }

        override fun initial(number: String): UiState {
            val result = when {
                number.toInt() == min -> UiState.Min(number)
                number.toInt() == max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
            return result
        }
    }
}

sealed class UiState : Serializable {
    abstract fun subscribeState(
        incrementButton: Button,
        decrementButton: Button,
        textView: TextView
    )

    data class Base(private val text: String) : UiState() {
        override fun subscribeState(
            incrementButton: Button,
            decrementButton: Button,
            textView: TextView
        ) {
            textView.text = text
            decrementButton.isEnabled = true
            incrementButton.isEnabled = true
        }
    }

    data class Max(private val text: String) : UiState() {
        override fun subscribeState(
            incrementButton: Button,
            decrementButton: Button,
            textView: TextView
        ) {
            textView.text = text
            incrementButton.isEnabled = false
            decrementButton.isEnabled = true
        }
    }

    data class Min(private val text: String) : UiState() {
        override fun subscribeState(
            incrementButton: Button,
            decrementButton: Button,
            textView: TextView
        ) {
            textView.text = text
            incrementButton.isEnabled = true
            decrementButton.isEnabled = false
        }
    }
}