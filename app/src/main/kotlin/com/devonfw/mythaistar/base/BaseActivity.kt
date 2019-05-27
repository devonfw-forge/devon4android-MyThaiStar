package com.devonfw.mythaistar.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.devonfw.mythaistar.App
import com.devonfw.mythaistar.AppComponent
import com.devonfw.mythaistar.navigation.Navigator
import com.facebook.drawee.backends.pipeline.Fresco


abstract class BaseActivity<C : Any> : AppCompatActivity() {

  val navigator by lazy(LazyThreadSafetyMode.NONE) { Navigator(this, containerId) }
  val toolbar: Toolbar by lazy(LazyThreadSafetyMode.NONE) { findViewById(toolbarId) as Toolbar }

  lateinit var component: C private set

  abstract val layoutId: Int
  abstract val containerId: Int
  abstract val toolbarId: Int
  abstract val initialState: () -> Screen

  override fun onCreate(inState: Bundle?) {
    component = inject(App.component(this))
    super.onCreate(inState)
    setContentView(layoutId)
    onViewCreated()
    navigator.restoreState(inState) {
      initialState() }
  }

  override fun onBackPressed() {
    navigator.handleBackButton()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    navigator.saveState(outState)
  }

  open protected fun onViewCreated() {}

  override fun onDestroy() {
    super.onDestroy()
    if(Fresco.hasBeenInitialized()) {
      Fresco.shutDown()
    }
  }

  abstract fun inject(component: AppComponent): C
}
