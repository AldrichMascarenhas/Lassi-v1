package com.example.lassi.di

import com.example.common_data.networking.di.networking_module
import com.example.credit_score_data.di.credit_score_data_module

object DataModules {
    private val base_data_module = networking_module

    val modules = credit_score_data_module + base_data_module
}