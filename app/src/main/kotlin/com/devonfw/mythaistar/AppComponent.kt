package com.devonfw.mythaistar

import com.devonfw.mythaistar.common.PerApp
import com.devonfw.mythaistar.base.BaseActivityModule
import com.devonfw.mythaistar.main.MainComponent
import dagger.Component

@PerApp
@Component
interface AppComponent {
  fun plusMain(modules: BaseActivityModule): MainComponent
}
