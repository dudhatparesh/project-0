package com.brighterbrain.project0.ui.main.viewitems

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.brighterbrain.project0.R

class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    @BindView(R.id.tv_name)
    lateinit var tvName: TextView

    init {
        ButterKnife.bind(this,item)
    }

}
