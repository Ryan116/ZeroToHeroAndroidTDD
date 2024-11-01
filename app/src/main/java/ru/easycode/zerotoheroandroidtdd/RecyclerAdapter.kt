package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ItemViewBinding

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    private val listTextView = ArrayList<CharSequence>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return listTextView.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listTextView[position]
        holder.bind(item)
    }

    class ItemViewHolder(private val binding: ItemViewBinding): ViewHolder(binding.root) {
        fun bind(item: CharSequence) {
            binding.elementTextView.text = item
        }
    }

    fun update(newList: List<CharSequence>) {
        val diffUtil = DiffUtilCallback(oldList = listTextView, newList = newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        listTextView.clear()
        listTextView.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}

private class DiffUtilCallback(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}