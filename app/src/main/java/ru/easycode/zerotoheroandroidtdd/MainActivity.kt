package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textWatcher = object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                binding.actionButton.isEnabled = binding.inputEditText.text.toString().length >= 3
            }
        }

        with(binding) {
            actionButton.setOnClickListener {
                val textView = createTextView(inputEditText.text.toString())
                contentLayout.addView(textView)
                inputEditText.text?.clear()
            }
            inputEditText.addTextChangedListener(textWatcher)
        }
    }

    private fun createTextView(text: String): TextView {
        return TextView(this)
            .apply {
                setText(text)
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val list = binding.contentLayout.children.toList().map {
            (it as TextView).text.toString()
        }
        outState.putStringArrayList(key, ArrayList(list))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val list = savedInstanceState.getSerializable(key) as List<String>
        list.forEach {
            binding.contentLayout.addView(createTextView(it))
        }
    }

    companion object {
        const val key = "uiState"
    }
}

private abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(s: Editable?) = Unit
}