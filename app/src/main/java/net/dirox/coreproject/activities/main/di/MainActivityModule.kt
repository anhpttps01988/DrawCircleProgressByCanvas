package net.dirox.coreproject.activities.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.dirox.coreproject.activities.main.view.TimelineFragment
import net.dirox.coreproject.common.di.BaseFragmentModule
import net.dirox.coreproject.common.di.scoped.FragmentScoped


@Module
abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, TimelineModule::class])
    abstract fun timeLineFragment(): TimelineFragment

}