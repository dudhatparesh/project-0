package com.brighterbrain.project0.ui.main.viewitems

import com.brighterbrain.project0.data.model.Item

interface ItemClickListener {
    fun onItemClick(item: Item)
}