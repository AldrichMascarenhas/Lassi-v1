package com.example.lassi.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class LassiTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, LassiTestTestApplication::class.java.name, context)
    }
}

@SuppressLint("Registered")
class LassiTestTestApplication : Application()