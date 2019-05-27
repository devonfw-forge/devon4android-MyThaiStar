package com.devonfw.mythaistar.navigation

import com.devonfw.mythaistar.base.Screen

/**
 * A class that allows to get a menu item ID or toolbar title from a Screen and vice-versa.
 */
class NavigationMap(vararg navigationItems: NavigationItem) {

  private val screenToNavigationItemId: Map<Class<out Screen>, Int> = navigationItems
      .filter { it.navigationId != null }
      .associateBy({ it.clazz.java }, { it.navigationId as Int })

  private val screenToToolbarTitleId: Map<Class<out Screen>, Int> = navigationItems
      .filter { it.toolbarTitle != null }
      .associateBy({ it.clazz.java }, { it.toolbarTitle as Int })

  private val navigationIdToScreen: Map<Int, () -> Screen> = navigationItems
      .filter { it.navigationId != null }
      .associateBy({ it.navigationId as Int }, { { it.clazz.java.getConstructor().newInstance() } })

  fun getNavigationItemId(screen: Screen) = screenToNavigationItemId[screen::class.java]

  fun getToolbarTitleId(screen: Screen) = screenToToolbarTitleId[screen::class.java]

  fun getScreen(navigationId: Int) = navigationIdToScreen[navigationId]?.invoke()
}
