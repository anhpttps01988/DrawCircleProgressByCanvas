package net.dirox.coreproject.data.source

import io.reactivex.Observable
import net.dirox.coreproject.data.source.response.CountryResponse
import retrofit2.Response


interface DataSource {

    fun getCountry(): Observable<Response<MutableList<CountryResponse>>>
}