package net.dirox.coreproject.data.source.di

import dagger.Binds
import dagger.Module
import net.dirox.coreproject.common.di.scoped.Remote
import net.dirox.coreproject.data.source.DataSource
import net.dirox.coreproject.data.source.remote.RemoteDataSource
import javax.inject.Singleton


@Module
abstract class DataRepositoryModule {

    @Remote
    @Binds
    @Singleton
    abstract fun remoteDataSource(dataSource: RemoteDataSource) : DataSource

}