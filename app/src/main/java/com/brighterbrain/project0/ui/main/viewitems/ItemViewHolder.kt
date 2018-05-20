package com.brighterbrain.project0.ui.main.viewitems

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.brighterbrain.project0.R

class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    @BindView(R.id.tv_name)
    lateinit var tvName: TextView

    @BindView(R.id.iv_image)
    lateinit var ivImage: ImageView

    @BindView(R.id.tv_desc)
    lateinit var tvDesc: TextView

    @BindView(R.id.tv_amount)
    lateinit var tvAmount: TextView

    @BindView(R.id.tv_location)
    lateinit var tvLocation: TextView

    init {
        ButterKnife.bind(this, item)
    }

}
