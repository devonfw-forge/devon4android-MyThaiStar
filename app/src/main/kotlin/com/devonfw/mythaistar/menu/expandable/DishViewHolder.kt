package com.devonfw.mythaistar.menu.expandable

import android.view.View
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.menu.api.models.Dish
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

/**
 * Created by MGWIZDAL on 2018-02-12.
 */
class DishViewHolder : ChildViewHolder {
    var dishDescription : TextView? = null

    constructor(itemView : View) : super(itemView) {
        dishDescription = itemView.findViewById(R.id.menu_item_description) as TextView?

    }
    fun onBind(dish : Dish){
        dishDescription?.setText(dish.description)
    }
}