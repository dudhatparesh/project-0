package com.brighterbrain.project0.ui.main.viewitems

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brighterbrain.project0.R
import com.brighterbrain.project0.data.model.Item
import com.bumptech.glide.Glide

class ItemAdapter constructor(var items:List<Item>, var context: Context?)
    : RecyclerView.Adapter<ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_item,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item.name
        Glide.with(context!!).load(Uri.parse(item.imagePath)).into(holder.ivImage)
        holder.tvDesc.text = item.description
        holder.tvAmount.text = StringBuilder().append(item.amount.toString())
                .append(" ")
                .append(item.currency)
                .toString()
    }

}
