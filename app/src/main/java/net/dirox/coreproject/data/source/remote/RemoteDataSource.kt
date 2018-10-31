package net.dirox.coreproject.data.source.remote

import io.reactivex.Observable
import net.dirox.coreproject.data.service.ServiceAPI
import net.dirox.coreproject.data.source.DataSource
import net.dirox.coreproject.data.source.response.CountryResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteDataSource @Inject constructor(retrofit: Retrofit) : DataSource {

    private var mService: ServiceAPI = retrofit.create(ServiceAPI::class.java)

    override fun getCountry(): Observable<Response<MutableList<CountryResponse>>> {
        return mService.getCountry()
    }
}