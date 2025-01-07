package ru.easycode.zerotoheroandroidtdd

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class AddBottomSheetFragment: BottomSheetDialogFragment(R.layout.add_layout) {
    private lateinit var viewModel: AddViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(AddViewModel::class.java)
        onBackPressedCallback = object :OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val input = view.findViewById<TextInputEditText>(R.id.addInputEditText)

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            viewModel.add(input.text.toString())
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}