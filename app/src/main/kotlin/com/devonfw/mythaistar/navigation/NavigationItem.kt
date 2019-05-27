package com.devonfw.mythaistar.navigation

import com.devonfw.mythaistar.base.Screen
import kotlin.reflect.KClass

/**
 * A class mapping IDs used by Android menu system, etc. with a Screen.
 */
data class NavigationItem(
        val clazz: KClass<out Screen>,
        val navigationId: Int?,
        val toolbarTitle: Int?
)
