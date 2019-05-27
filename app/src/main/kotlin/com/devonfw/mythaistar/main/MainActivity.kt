package com.devonfw.mythaistar.main

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import com.devonfw.mythaistar.AppComponent
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.common.bindView
import com.devonfw.mythaistar.base.BaseActivity
import com.devonfw.mythaistar.base.BaseActivityModule
import com.devonfw.mythaistar.booking.BookingFragment
import com.devonfw.mythaistar.contacts.ContactsFragment
import com.devonfw.mythaistar.gallery.GalleryFragment
import com.devonfw.mythaistar.home.HomeFragment
import com.devonfw.mythaistar.location.LocationFragment
import com.devonfw.mythaistar.login.LoginFragment
import com.devonfw.mythaistar.menu.MenuFragment

import com.devonfw.mythaistar.navigation.NavigationItem
import com.devonfw.mythaistar.navigation.NavigationMap
import com.devonfw.mythaistar.news.NewsFragment
import com.devonfw.mythaistar.view.OnDrawerClosedListener

class MainActivity : BaseActivity<MainComponent>() {


    private val drawerLayout: DrawerLayout by bindView(R.id.drawer_layout)
    private val navigationView: NavigationView by bindView(R.id.navigation_view)

    private val drawerListener = OnDrawerClosedListener()

    override val layoutId = R.layout.activity_main
    override val containerId = R.id.container
    override val toolbarId = R.id.toolbar
    override val initialState = { HomeFragment() }

    override fun onViewCreated() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(GravityCompat.START, true) }
        drawerLayout.addDrawerListener(drawerListener)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerListener.withAction { SCREEN_MAP.getScreen(menuItem.itemId)?.let { navigator.show(it) } }
            drawerLayout.closeDrawer(GravityCompat.START, true)
            false
        }
        navigator.addOnShowScreenListener {
            SCREEN_MAP.getNavigationItemId(it)?.let { navigationView.menu.findItem(it)?.isChecked = true }
            SCREEN_MAP.getToolbarTitleId(it)?.let { toolbar.setTitle(it) }
            when (it) {
                is HomeFragment -> toolbar.setLogo(R.drawable.ic_logo)
                else -> toolbar.logo = null
            }
        }
        navigator.addBackButtonInterceptor {
            drawerLayout.closeDrawer(GravityCompat.START, true)
            drawerLayout.isDrawerVisible(GravityCompat.START)
        }
    }

    override fun inject(component: AppComponent) = component.plusMain(BaseActivityModule(this))

    private companion object {
        val SCREEN_MAP = NavigationMap(
                NavigationItem(HomeFragment::class, R.id.navigation_home, R.string.screen_home_title),
                NavigationItem(MenuFragment::class, R.id.navigation_restaurant_menu, R.string.screen_menu_title),
                NavigationItem(BookingFragment::class, R.id.navigation_book_table, R.string.screen_booking_title),
                NavigationItem(LocationFragment::class, R.id.navigation_location, R.string.screen_location),
                NavigationItem(GalleryFragment::class, R.id.navigation_gallery, R.string.screen_gallery),
                NavigationItem(NewsFragment::class, R.id.navigation_news, R.string.screen_news),
                NavigationItem(ContactsFragment::class, R.id.navigation_contacts, R.string.screen_contacts),
                NavigationItem(LoginFragment::class, R.id.navigation_login, R.string.screen_login ))
    }
}
