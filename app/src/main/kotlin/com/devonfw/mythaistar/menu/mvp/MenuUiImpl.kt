package com.devonfw.mythaistar.menu.mvp

import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.TextView
import com.devonfw.mythaistar.menu.MenuUi
import com.devonfw.mythaistar.menu.api.models.DishImage
import com.devonfw.mythaistar.menu.expandable.DishEntity
import java.util.ArrayList

/**
 * Created by MGWIZDAL on 2018-02-27.
 */
interface MenuUiImpl : MenuUi {
    val recyclerView: RecyclerView
    val spinningBar : ProgressBar
    val wrongSearchTextView : TextView
    var isReady : Boolean
    var dishList : ArrayList<DishEntity>?
    var dishesImagesList : MutableList<DishImage>

    fun onNetworkError()
}