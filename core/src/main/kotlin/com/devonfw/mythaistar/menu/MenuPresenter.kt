package com.devonfw.mythaistar.menu

import com.devonfw.mythaistar.base.BasePresenter
import com.devonfw.mythaistar.common.PerScreen
import javax.inject.Inject

@PerScreen
open class MenuPresenter<M : MenuUi> @Inject constructor() : BasePresenter<M>()

