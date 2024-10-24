package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.TextViewHolder>() {

    private val listTextView = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_view, parent, false) as TextView
        return TextViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTextView.size
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val item = listTextView[position]
        holder.bind(item)
    }

    class TextViewHolder(private val rootView: TextView): ViewHolder(rootView) {
        fun bind(item: String) {
            rootView.text = item
        }
    }

    fun addItem(item: String) {
        listTextView.add(item)
        notifyItemInserted(listTextView.size - 1)
    }

    fun saveList(bundle: Bundle) {
        bundle.putStringArrayList(KEY, listTextView)
    }

    fun restoreList(bundle: Bundle) {
        val list = bundle.getStringArrayList(KEY) as ArrayList<String>
        listTextView.addAll(list)
        notifyItemRangeInserted(0, listTextView.size)
    }

    companion object {
        const val KEY = "key"
    }
}