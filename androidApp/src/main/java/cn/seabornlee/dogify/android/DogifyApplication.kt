package cn.seabornlee.dogify.android

import android.app.Application
import cn.seabornlee.dogify.di.initKoin

class DogifyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}