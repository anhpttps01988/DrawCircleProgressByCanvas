package net.dirox.coreproject.common.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import net.dirox.coreproject.CoreApp
import net.dirox.coreproject.data.source.di.DataRepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    DataRepositoryModule::class,
    BaseActivityModuleBinding::class,
    AndroidSupportInjectionModule::class,
    NetModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: CoreApp)

    override fun inject(instance: DaggerApplication?)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build() : AppComponent
    }
}