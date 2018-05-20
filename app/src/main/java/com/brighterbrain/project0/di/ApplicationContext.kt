package com.brighterbrain.project0.di

import kotlin.annotation.Retention
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
