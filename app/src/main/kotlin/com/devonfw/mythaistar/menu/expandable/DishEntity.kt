package com.devonfw.mythaistar.menu.expandable

import com.devonfw.mythaistar.menu.api.models.Dish
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

/**
 * Created by MGWIZDAL on 2018-02-12.
 */
class DishEntity : ExpandableGroup<Dish>  {
    //This weird constructor is required by Thoughtbot(it could be just List<Dish>)
    constructor(title : String? , items: List<Dish>) : super(title, items)
}