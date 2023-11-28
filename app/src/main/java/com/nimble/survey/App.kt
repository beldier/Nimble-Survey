package com.nimble.survey

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}