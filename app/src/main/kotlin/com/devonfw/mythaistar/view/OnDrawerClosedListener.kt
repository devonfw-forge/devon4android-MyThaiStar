package com.devonfw.mythaistar.view

import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import android.support.v4.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import android.view.View

/**
 * A [DrawerLayout.DrawerListener] that executes an action when the drawer is closed.
 */
class OnDrawerClosedListener : DrawerLayout.DrawerListener {

  private var onClosedAction: (() -> Unit)? = null

  override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
    onClosedAction?.let { lockDrawer(drawerView, true) }
  }

  override fun onDrawerClosed(drawerView: View) {
    onClosedAction?.let {
      it()
      lockDrawer(drawerView, false)
    }
    onClosedAction = null
  }

  override fun onDrawerOpened(drawerView: View) {}

  override fun onDrawerStateChanged(newState: Int) {}

  private fun lockDrawer(drawerView: View, lock: Boolean) {
    val parent = drawerView.parent
    if (parent is DrawerLayout) {
      parent.setDrawerLockMode(if (lock) LOCK_MODE_LOCKED_CLOSED else LOCK_MODE_UNLOCKED, drawerView)
    }
  }

  /**
   * Adds an action to be executed when the drawer is closed.
   */
  fun withAction(action: () -> Unit) {
    onClosedAction = action
  }
}
