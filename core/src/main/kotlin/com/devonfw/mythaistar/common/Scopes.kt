package com.devonfw.mythaistar.common

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerApp

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerScreen
