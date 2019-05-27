package com.devonfw.mythaistar.navigation


import android.app.FragmentManager
import android.os.Bundle
import android.support.v4.app.FragmentActivity

import com.devonfw.mythaistar.base.Screen

/**
 * A wrapper class for [FragmentManager].
 *
 * @param activity A [FragmentActivity] upon which [Navigator] should operate.
 * @param containerId An ID of a view that should be used to change content of the [activity].
 */
class Navigator(private val activity: FragmentActivity, private val containerId: Int) {

  private val fragmentManager: FragmentManager = activity.fragmentManager
  private val onShowFragmentListeners = mutableSetOf<(Screen) -> Unit>()
  private val backButtonInterceptors = mutableListOf<() -> Boolean>()

  /**
   * Save the current state of the screen in a [outState] Bundle.
   */
  fun saveState(outState: Bundle) {
    fragmentManager.putFragment(outState, STATE_SCREEN, fragmentManager.findScreenById(containerId))
  }

  /**
   * Restores the previous state of the screen from a [inState] Bundle. If it
   * is null than the view container is initialized with a Screen passed in an [initialState].
   */
  fun restoreState(inState: Bundle?, initialState: () -> Screen) {
    if (null == inState) {
      fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
      navigate(initialState())
    } else {
      onShowFragmentListeners.forEach { it(fragmentManager.getScreen(inState, STATE_SCREEN)) }
    }
  }

  /**
   * Adds a [listener] that will be executed when a new screen is shown.
   */
  fun addOnShowScreenListener(listener: (Screen) -> Unit) = onShowFragmentListeners.add(listener)

  /**
   * Removes a [listener] that will be executed when a new screen is shown.
   */
  fun removeOnShowListener(listener: (Screen) -> Unit) = onShowFragmentListeners.remove(listener)

  /**
   * Adds an [interceptor] for a back button. Interceptor should return true when the back button
   * event is consumed, false otherwise.
   */
  fun addBackButtonInterceptor(interceptor: () -> Boolean) = backButtonInterceptors.add(interceptor)

  /**
   * Removes an [interceptor] for a back button.
   */
  fun removeBackButtonInterceptor(interceptor: () -> Boolean) = backButtonInterceptors.remove(interceptor)

  /**
   * Navigates to a [screen].
   */
  fun show(screen: Screen) {
    fragmentManager.findScreenById(containerId)?.let {
      if (it.id != screen.id) {
        if (fragmentManager.findScreenByTag(screen.id) != null) {
          popBackStack(screen)
        } else {
          navigate(screen)
        }
      }
    }
  }

  /**
   * Handles back button action. Resolves any interceptor added to the [Navigator]
   * or custom back button handling by a currently visible [Screen].
   */
  fun handleBackButton() {
    if (interceptDefaultAction()) {
      return
    }
    if (fragmentManager.backStackEntryCount <= 1) {
      activity.finish()
    } else {
      fragmentManager.findScreenById(containerId)?.let {
        if (!it.handleBackButton()) {
          fragmentManager.popBackStack()
          fragmentManager.findScreenById(containerId)?.let { screen ->
            onShowFragmentListeners.forEach { it(screen) }
          }
        }
      }
    }
  }

  private fun interceptDefaultAction(): Boolean {
    var intercept = false
    backButtonInterceptors.forEach { intercept = intercept || it() }
    return intercept
  }

  private fun popBackStack(fragment: Screen) {
    fragmentManager.popBackStack(fragment.id, 0)
    onShowFragmentListeners.forEach { it(fragment) }
  }

  private fun navigate(fragment: Screen) {
    fragmentManager
        .beginTransaction()
        .replace(containerId, fragment, fragment.id)
        .addToBackStack(fragment.id)
        .commit()
    onShowFragmentListeners.forEach { it(fragment) }
  }

  private companion object {
    const val STATE_SCREEN = "Navigator.ScreenState"
  }

  private fun FragmentManager.findScreenById(id: Int) = findFragmentById(id) as Screen?

  private fun FragmentManager.findScreenByTag(tag: String) = findFragmentByTag(tag) as Screen?

  private fun FragmentManager.getScreen(inState: Bundle?, key: String) = getFragment(inState, key) as Screen
}
