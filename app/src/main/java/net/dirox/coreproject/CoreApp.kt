package net.dirox.coreproject

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.dirox.coreproject.common.di.DaggerAppComponent

class CoreApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}