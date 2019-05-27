package com.devonfw.mythaistar.menu

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.menu.api.models.Dish

/**
 * Created by MGWIZDAL on 2018-02-06.
 */
class MenuRecyclerAdapter(var context : Context, var dishList : List<Dish>) :
        RecyclerView.Adapter<MenuRecyclerAdapter.MyViewHolder>(){


    override fun onBindViewHolder(holder: MenuRecyclerAdapter.MyViewHolder, position: Int) {
        holder.bindItems(position, dishList)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuRecyclerAdapter.MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_menu_recycler_item,parent,false)
        var holder = MenuRecyclerAdapter.MyViewHolder(view, context)
        return holder
    }


    override fun getItemCount(): Int {
        return dishList.size
    }

    class MyViewHolder(itemView : View, var context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(position: Int, itemNameList: List<Dish>) {
            var dishNameTV = itemView.findViewById(R.id.menu_item_name) as TextView
            var dishPriceTV = itemView.findViewById(R.id.menu_item_price) as TextView
            val item = itemNameList[position]
            dishNameTV.text = item.name
            var priceString = context.getString(R.string.dollar_sign) + item.price.toString()
            dishPriceTV.text = priceString
        }
    }
}