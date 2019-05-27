package com.devonfw.mythaistar.view

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ViewSwitcher
import com.devonfw.mythaistar.R

/**
 * A [ViewSwitcher] that adds a sliding animation on view transition.
 */
class SlideViewSwitcher @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewSwitcher(context, attrs) {

  private val slideInRightAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
  private val slideOutLeftAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_left)
  private val slideInLeftAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
  private val slideOutRightAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)

  /**
   * Changes a transition animation to slide from left to right.
   */
  fun withSlideToRight(): SlideViewSwitcher {
    inAnimation = slideInLeftAnimation
    outAnimation = slideOutRightAnimation
    return this
  }

  /**
   * Changes a transition animation to slide from right to left.
   */
  fun withSlideToLeft(): SlideViewSwitcher {
    inAnimation = slideInRightAnimation
    outAnimation = slideOutLeftAnimation
    return this
  }
}
