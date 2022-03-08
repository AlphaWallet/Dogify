package cn.seabornlee.dogify.android

import android.app.Application
import cn.seabornlee.dogify.di.initKoin
import cn.seabornlee.dogify.di.viewModelModule
import org.koin.android.ext.koin.androidContext

class DogifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@DogifyApplication)
            modules(viewModelModule)
        }
    }
}