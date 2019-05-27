package com.devonfw.mythaistar.main

import com.devonfw.mythaistar.common.PerScreen
import com.devonfw.mythaistar.base.BaseActivityModule
import com.devonfw.mythaistar.booking.BookingFragment
import com.devonfw.mythaistar.contacts.ContactsFragment
import com.devonfw.mythaistar.gallery.GalleryFragment
import com.devonfw.mythaistar.home.HomeFragment
import com.devonfw.mythaistar.location.LocationFragment
import com.devonfw.mythaistar.login.LoginFragment
import com.devonfw.mythaistar.menu.ApiModule
import com.devonfw.mythaistar.menu.MenuFragment
import com.devonfw.mythaistar.news.NewsFragment
import dagger.Subcomponent



@PerScreen
@Subcomponent(modules = arrayOf(BaseActivityModule::class, ApiModule::class))
interface MainComponent {

  fun inject(into: HomeFragment)

  fun inject(into: MenuFragment)

  fun inject(into: BookingFragment)

  fun inject(into : LocationFragment)

  fun inject(into : GalleryFragment)

  fun inject(into : NewsFragment)

  fun inject(into : ContactsFragment)

  fun inject(into : LoginFragment)

}
