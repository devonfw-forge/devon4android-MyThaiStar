package com.devonfw.mythaistar.menu.expandable

import android.view.LayoutInflater
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import android.view.ViewGroup
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.menu.api.models.Dish
import com.devonfw.mythaistar.menu.api.models.DishImage



/**
 * Created by MGWIZDAL on 2018-02-12.
 */
class DishEntityAdapter : ExpandableRecyclerViewAdapter<DishEntityViewHolder, DishViewHolder> {
    constructor(groups: List<ExpandableGroup<*>>) : super(groups)
    var images: List<DishImage>? = null

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): DishEntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_menu_recycler_item, parent, false)
        return DishEntityViewHolder(view)
    }

    override fun onBindGroupViewHolder(holder: DishEntityViewHolder, flatPosition: Int, group: ExpandableGroup<*>) {
        holder.dishTitle?.setText(group.title)

        val dish = group.items[0] as Dish
        val dishPrice = "$" + dish.price.toString()
        holder.dishPrice?.setText(dishPrice)

        var content: String? = null
        run loop@{
            images?.forEach { index ->
                if (index.id == dish.imageId) {
                    content = index.content
                    return@loop
                }
            }
        }
        holder.dishImage?.setImageURI(content)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_menu_recycler_subitem, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: DishViewHolder, flatPosition: Int, group: ExpandableGroup<*>, childIndex: Int) {
        val dish = (group as DishEntity).getItems().get(childIndex)
        holder.onBind(dish)
    }

}


