package com.devonfw.mythaistar.base

open class BasePresenter<V> {

  protected var view: V? = null

  /**
   * Binds a [view] to this presenter. Must be called before any operations
   * are dispatched to the [view]. Call an [unbindView] method after presenter
   * no longer requires the view.
   */
  open fun bindView(view: V) {
    val previousView = this.view
    if (previousView != null) {
      throw IllegalStateException("Previous view $previousView is not unbound.")
    }
    this.view = view
  }

  /**
   * Unbinds a [view] from this presenter. When a [view] to unbind is different
   * than the one passed in a [bindView] method [IllegalStateException] is thrown.
   */
  open fun unbindView(view: V) {
    val previousView = this.view
    if (previousView !== view) {
      throw IllegalStateException("Trying to unbind wrong view. previousView = $previousView, view to unbind = $view.")
    } else {
      this.view = null
    }
  }
}
