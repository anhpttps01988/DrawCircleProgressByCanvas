package net.dirox.coreproject.data.source

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.dirox.coreproject.common.di.scoped.Remote
import net.dirox.coreproject.data.source.response.CountryResponse
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataRepository @Inject constructor(@Remote var remoteDataSource: DataSource) : DataSource {

    override fun getCountry(): Observable<Response<MutableList<CountryResponse>>> {
        return remoteDataSource.getCountry().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
    }


}