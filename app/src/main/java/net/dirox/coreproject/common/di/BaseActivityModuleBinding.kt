package net.dirox.coreproject.common.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.dirox.coreproject.activities.main.di.MainActivityModule
import net.dirox.coreproject.activities.main.view.MainActivity
import net.dirox.coreproject.common.di.scoped.ActivityScoped

@Module
abstract class BaseActivityModuleBinding {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [BaseActivityModule::class, MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

}