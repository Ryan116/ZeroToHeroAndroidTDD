package ru.easycode.zerotoheroandroidtdd

import android.os.Build
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
    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.titleTextView)
        button = findViewById(R.id.removeButton)
        linearLayout = findViewById(R.id.rootLayout)
        button?.setOnClickListener {
            state = State.Removed
            (state as State.Removed).remove(linearLayout, textView, button)
        }
        savedInstanceState?.let {
            state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(STATE_KEY, State::class.java) as State
            } else {
                savedInstanceState.getSerializable(STATE_KEY) as State
            }
            (state as? State.Removed)?.remove(linearLayout, textView, button)
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

sealed class State : Serializable {

    data object Initial : State()

    data object Removed : State() {
        fun remove(linearLayout: LinearLayout?, textView: TextView?, button: Button?) {
            linearLayout?.removeView(textView)
            button?.isEnabled = false
        }
    }
}