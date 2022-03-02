package com.example.lassi.di

import org.koin.core.module.Module

object GlobalModules {

    @JvmField
    val modules: List<Module> = DataModules.modules + FeatureModules.modules


}