package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemLayoutBinding

class RecyclerAdapter(
    private val deleteItem: DeleteItemUi
): RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    private val list = mutableListOf<ItemUi>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            deleteItem,
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    fun update(newList: List<ItemUi>) {
        val diffUtil = DiffUtilCallback(oldList = list, newList = newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    class ItemViewHolder(
        private val deleteItem: DeleteItemUi,
        private val binding: ItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(itemUi: ItemUi) {
            itemUi.show(binding.elementTextView)
            itemView.setOnClickListener {
                itemUi.delete(deleteItem)
            }
        }
    }
}

private class DiffUtilCallback(
    private val oldList: List<ItemUi>,
    private val newList: List<ItemUi>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].areItemsSame(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}