package net.dirox.coreproject.common.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.dirox.coreproject.activities.main.viewmodel.MainActivityViewModel
import net.dirox.coreproject.common.di.scoped.ViewModelKey
import net.dirox.coreproject.common.viewmodel.ViewModelFactory


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun mainActivityViewModel(viewModel: MainActivityViewModel) : ViewModel

    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory


}