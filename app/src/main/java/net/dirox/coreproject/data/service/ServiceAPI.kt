package net.dirox.coreproject.data.service

import io.reactivex.Observable
import net.dirox.coreproject.data.source.response.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {

    @GET("countries/sending")
    fun getCountry(): Observable<Response<MutableList<CountryResponse>>>

}