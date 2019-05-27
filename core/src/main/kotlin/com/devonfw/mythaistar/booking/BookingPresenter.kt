package com.devonfw.mythaistar.booking

import com.devonfw.mythaistar.base.BasePresenter
import com.devonfw.mythaistar.common.PerScreen
import com.devonfw.mythaistar.table.Table
import com.devonfw.mythaistar.table.TableDB
import com.devonfw.mythaistar.user.User
import java.util.regex.Pattern

import javax.inject.Inject

@PerScreen
open class BookingPresenter<B : BookingUi> @Inject constructor() : BasePresenter<B>() {}




