package ru.easycode.zerotoheroandroidtdd

import android.widget.TextView

data class ItemUi(
    private val id: Long,
    private val text: String
) {
    fun areItemsSame(other: ItemUi) = id == other.id

    fun delete(deleteItemUi: DeleteItemUi) = deleteItemUi.delete(id)

    fun show(textView: TextView) = textView.setText(text)
}
