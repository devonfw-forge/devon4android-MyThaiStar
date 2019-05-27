package com.devonfw.mythaistar.menu.mvp

import android.os.Build
import android.view.View
import com.devonfw.mythaistar.menu.MenuPresenter
import com.devonfw.mythaistar.menu.api.MenuClient
import com.devonfw.mythaistar.menu.api.models.Dish
import com.devonfw.mythaistar.menu.api.models.DishImage
import com.devonfw.mythaistar.menu.api.models.DishResponseBody
import com.devonfw.mythaistar.menu.api.models.MenuRequest
import com.devonfw.mythaistar.menu.expandable.DishEntity
import com.devonfw.mythaistar.menu.expandable.DishEntityAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by MGWIZDAL on 2018-02-27.
 */
class MenuPresenterImpl @Inject constructor() : MenuPresenter<MenuUiImpl>() {
    private lateinit var dish: MenuRequest
    private lateinit var dishCall: Call<DishResponseBody>
    var dishEntityAdapter : DishEntityAdapter? = null

    fun startCall(retrofit: Retrofit, query : String = "", maxPrice: Int = 100) {
        view?.wrongSearchTextView?.visibility = View.INVISIBLE
        if (view?.isReady == null) view?.isReady =false
        val list: ArrayList<DishEntity> = arrayListOf()
        if (!view?.isReady!!) {
            dish = MenuRequest(searchBy = query, maxPrice = maxPrice)
            dishCall = retrofit.create<MenuClient>(MenuClient::class.java).postAllDishes(dish)

            dishCall.enqueue(object : Callback<DishResponseBody> {
                override fun onResponse(call: Call<DishResponseBody>, response: Response<DishResponseBody>) {
                    val resultList = response.body()?.result
                    if(resultList?.size == 0){
                        view?.spinningBar?.visibility = View.INVISIBLE
                        view?.wrongSearchTextView?.visibility = View.VISIBLE
                    }
                    val dishesList: MutableList<Dish> = mutableListOf()
                    val dishesImagesList: MutableList<DishImage> = mutableListOf()

                    for (index: Int in resultList!!.indices) {
                        val dishContainer = resultList[index].dish
                        val dishImageContainer = resultList[index].image
                        if (dishContainer is Dish) dishesList.add(dishContainer)
                        if (dishImageContainer is DishImage) dishesImagesList.add(dishImageContainer)
                    }

                    for (index: Int in dishesList.indices) {
                        val title = dishesList[index].name
                        val listContainer: List<Dish> = listOf(dishesList[index])
                        list.add(DishEntity(title, listContainer))
                    }
                    dishEntityAdapter = DishEntityAdapter(list)
                    dishEntityAdapter?.images = dishesImagesList

                    saveToView(view!!, dishEntityAdapter!!, true, list, dishesImagesList )
                    view?.spinningBar?.visibility = View.INVISIBLE
                }

                override fun onFailure(call: Call<DishResponseBody>, t: Throwable) {
                    view?.onNetworkError()
                }
            })
        }
        else{
            dishEntityAdapter = DishEntityAdapter(view?.dishList!!)
            dishEntityAdapter?.images = view?.dishesImagesList
            view?.recyclerView?.adapter = dishEntityAdapter
            view?.spinningBar?.visibility = View.INVISIBLE
        }
    }
    fun saveToView(view : MenuUiImpl, adapter: DishEntityAdapter, isReady : Boolean, list : ArrayList<DishEntity>, dishesImagesList: MutableList<DishImage> ){
        view.recyclerView.adapter = adapter
        view.isReady = isReady
        view.dishList = list
        view.dishesImagesList = dishesImagesList
    }
}