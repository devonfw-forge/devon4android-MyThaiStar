package com.devonfw.mythaistar.menu.expandable

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.facebook.drawee.view.SimpleDraweeView

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

/**
 * Created by MGWIZDAL on 2018-02-12.
 */
class DishEntityViewHolder : GroupViewHolder {
    var dishTitle: TextView? = null
    var dishPrice: TextView? = null
    var dishImage: SimpleDraweeView? = null


    constructor(itemView: View) : super(itemView) {
        dishTitle = itemView.findViewById(R.id.menu_item_name) as TextView?
        dishPrice = itemView.findViewById(R.id.menu_item_price) as TextView?
        dishImage = itemView.findViewById(R.id.menu_item_image) as SimpleDraweeView?

    }
}