package ru.easycode.zerotoheroandroidtdd

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class DetailsBottomSheetFragment: BottomSheetDialogFragment(R.layout.details_layout) {

    companion object {
        fun newInstance(itemId: Long): DetailsBottomSheetFragment {
            val instance = DetailsBottomSheetFragment()
            instance.arguments = Bundle().apply {
                putLong(KEY, itemId)
            }
            return instance
        }

        private const val KEY = "itemIdToDelete"
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DetailsViewModel::class.java)
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
        viewModel = (activity as ProvideViewModel).viewModel(DetailsViewModel::class.java)

        val itemId = requireArguments().getLong(KEY)

        val textView = view.findViewById<TextView>(R.id.itemTextView)
        val itemInputEditText = view.findViewById<TextInputEditText>(R.id.itemInputEditText)
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)
        val updateButton = view.findViewById<Button>(R.id.updateButton)

        deleteButton.setOnClickListener {
            viewModel.delete(itemId)
            dismiss()
        }

        updateButton.setOnClickListener {
            viewModel.update(itemId, itemInputEditText.text.toString())
            dismiss()
        }
        viewModel.liveData.observe(viewLifecycleOwner) {
            textView.text = it
            itemInputEditText.setText(it)
            itemInputEditText.setSelection(it.length)
        }

        viewModel.init(itemId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}