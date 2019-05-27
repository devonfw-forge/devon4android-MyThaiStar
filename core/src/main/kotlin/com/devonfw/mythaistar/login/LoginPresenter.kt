package com.devonfw.mythaistar.login

import com.devonfw.mythaistar.base.BasePresenter
import com.devonfw.mythaistar.common.PerScreen
import javax.inject.Inject

/**
 * Created by MGWIZDAL on 2018-03-09.
 */
@PerScreen
open class LoginPresenter<L : LoginUi>  @Inject constructor() : BasePresenter<L>()



