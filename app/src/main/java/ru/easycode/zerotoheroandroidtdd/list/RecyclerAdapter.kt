package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.ViewGroup
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
        listTextView.clear()
        listTextView.addAll(newList)
        notifyDataSetChanged()
    }
}