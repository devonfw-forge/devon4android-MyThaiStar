package com.devonfw.mythaistar.home

import android.os.Bundle
import android.view.View
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.common.bindView
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.booking.BookingFragment
import com.devonfw.mythaistar.menu.MenuFragment
import com.devonfw.mythaistar.main.MainComponent

class HomeFragment : BaseFragment<MainComponent, HomeUi, HomePresenter>(), HomeUi {

   val inviteFriendsItem: HomeItemView by bindView(R.id.item_invite_friends)
  private val restaurantMenuItem: HomeItemView by bindView(R.id.item_restaurant_menu)

  override val layoutId = R.layout.fragment_home
  override val ui = this

  override fun onViewCreated(view: View, inState: Bundle?) {
    super.onViewCreated(view, inState)
    inviteFriendsItem.setContent(
        image = R.drawable.home_item_invite,
        title = R.string.screen_home_item_invite_title,
        description = R.string.screen_home_item_invite_description,
        buttonLabel = R.string.screen_home_item_book_table_button_label,
        buttonAction = { navigator.show(BookingFragment()) })
    restaurantMenuItem.setContent(
        image = R.drawable.home_item_menu,
        title = R.string.screen_home_item_menu_title,
        description = R.string.screen_home_item_menu_description,
        buttonLabel = R.string.screen_home_item_menu_button_label,
        buttonAction = { navigator.show(MenuFragment()) })
  }

  override fun inject(component: MainComponent) = component.inject(this)
}
