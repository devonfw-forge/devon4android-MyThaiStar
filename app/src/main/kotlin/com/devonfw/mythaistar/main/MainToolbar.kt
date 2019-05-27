package com.devonfw.mythaistar.main

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import com.devonfw.mythaistar.R

class MainToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : Toolbar(context, attrs) {

  init {
    setNavigationIcon(R.drawable.ic_navigation_menu)
  }
}
