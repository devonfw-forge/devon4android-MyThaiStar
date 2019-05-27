package com.devonfw.mythaistar.menu

import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.common.bindView
import com.devonfw.mythaistar.dialog.FullScreenDialog
import com.devonfw.mythaistar.home.HomeFragment
import com.devonfw.mythaistar.main.MainComponent
import com.devonfw.mythaistar.menu.api.models.DishImage
import com.devonfw.mythaistar.menu.expandable.DishEntity
import com.devonfw.mythaistar.menu.mvp.MenuPresenterImpl
import com.devonfw.mythaistar.menu.mvp.MenuUiImpl
import com.facebook.drawee.backends.pipeline.Fresco
import java.util.*

class MenuFragment : BaseFragment<MainComponent, MenuUiImpl, MenuPresenterImpl>(),
        MenuUiImpl,
        FilterDialog.MaxPriceDialogListener {
    override val recyclerView: RecyclerView by bindView(R.id.recycler_view)
    override val spinningBar: ProgressBar by bindView(R.id.progress_bar)
    override val wrongSearchTextView: TextView by bindView(R.id.wrong_search)

    override val layoutId = R.layout.fragment_menu
    override val ui = this
    val KEY_IS_READY = "recyclerViewReady"
    val KEY_LIST = "recyclerViewList"
    val KEY_IMAGE_LIST = "recyclerViewImageList"
    override var dishList: ArrayList<DishEntity>? = null
    override var dishesImagesList: MutableList<DishImage> = mutableListOf()
    override var isReady = false
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    lateinit var queryPermanent: String
    override fun onViewCreated(view: View, inState: Bundle?) {
        super.onViewCreated(view, inState)
        Log.i(LOG_TAG, "Fresco initilize in onViewCreated")
        Fresco.initialize(activity.applicationContext)
        setHasOptionsMenu(true)
        mLayoutManager = LinearLayoutManager(activity.applicationContext)
        recyclerView.layoutManager = mLayoutManager
        spinningBar.visibility = View.VISIBLE
        queryPermanent = getString(R.string.empty_string)
        checkState(inState)
        presenter.startCall(retrofit)
    }

    private fun checkState(state: Bundle?) {
        if (state != null) {
            if (state.getBoolean(KEY_IS_READY)) isReady = true
            dishList = state.getParcelableArrayList<DishEntity>(KEY_LIST)
            val listHelper = state.getParcelableArrayList<DishImage>(KEY_IMAGE_LIST)
            dishesImagesList = listHelper
        }
    }


    override fun onNetworkError() {
        val dialog = FullScreenDialog()
        val ft: FragmentManager = fragmentManager
        dialog.show(ft, FullScreenDialog.TAG)
    }

    override fun handleBackButton(): Boolean {
        spinningBar.visibility = View.INVISIBLE
        navigator.show(HomeFragment())
        return true
    }

    override fun inject(component: MainComponent) {
        component.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(LOG_TAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_READY, isReady)
        outState.putParcelableArrayList(KEY_LIST, dishList)
        val list = dishesImagesList as ArrayList<DishImage>
        outState.putParcelableArrayList(KEY_IMAGE_LIST, list)
        spinningBar.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.menu_menu, menu)
        val searchItem = menu.findItem(R.id.menu_search_bar)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search_by)
        searchView.isIconified = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    queryPermanent = query
                    isReady = false
                    spinningBar.visibility = View.VISIBLE
                    presenter.startCall(retrofit, query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filter -> showFilterDialog()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun showFilterDialog() {
        val fragmentManager = fragmentManager
        val filterDialog = FilterDialog()
        filterDialog.setTargetFragment(this, 1)
        filterDialog.show(fragmentManager, "TAG")
    }

    override fun onFinishDialog(maxPrice: Int) {
        isReady = false
        spinningBar.visibility = View.VISIBLE
        presenter.startCall(retrofit, query = queryPermanent, maxPrice = maxPrice)
    }

}
