package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
                titleTextView.text = inputEditText.text.toString()
                inputEditText.text?.clear()
            }
            inputEditText.addTextChangedListener(textWatcher)
        }
    }
}

private abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(s: Editable?) = Unit
}