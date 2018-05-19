package com.birghterbrain.sellit.di

import kotlin.annotation.Retention
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
