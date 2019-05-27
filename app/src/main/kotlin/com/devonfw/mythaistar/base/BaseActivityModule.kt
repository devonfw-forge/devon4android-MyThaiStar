package com.devonfw.mythaistar.base

import com.devonfw.mythaistar.common.PerScreen
import dagger.Module
import dagger.Provides


@Module
class BaseActivityModule(val activity: BaseActivity<*>) {

  @Provides @PerScreen fun provideApplication() = activity.application
  @Provides @PerScreen fun provideToolbar() = activity.toolbar

  @Provides @PerScreen fun provideNavigator() = activity.navigator

}
