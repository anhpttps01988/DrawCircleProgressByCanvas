package net.dirox.coreproject.activities.main.viewmodel

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import net.dirox.coreproject.common.viewmodel.BaseViewModel
import net.dirox.coreproject.data.source.DataRepository
import net.dirox.coreproject.data.source.response.CountryResponse
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(var dataRepository: DataRepository) : BaseViewModel(),
    LifecycleObserver,
    MainActivityContract.ViewModelContract {

    companion object {
        val TAG = "MainActivityViewModel"
    }

    var countLiveData = MutableLiveData<String>()
    var compositeDisposable = CompositeDisposable()
    private var count = 0

    override fun start() {
        super.start()
        countLiveData.value = "Count ${count++}"

    }

    override fun stop() {
        super.stop()
        compositeDisposable.clear()
    }

    override fun increaseCount() {
        countLiveData.value = "Count ${count++}"
    }

    override fun loadCountryList() {
        compositeDisposable.add(dataRepository.getCountry()
            .retryWhen { it -> it.take(4).delay(2000,TimeUnit.MILLISECONDS)}
            .flatMap { t: Response<MutableList<CountryResponse>> ->
            (if (t.isSuccessful) {
                Observable.just(t.body())
            } else
                Observable.error { Exception(t.errorBody().toString()) })
        }.subscribe({ response ->
            onCountryResponseSuccess(response)
        }, { error ->
            onCountryResponseError(error)
        }))
    }

    private fun onCountryResponseSuccess(response: MutableList<CountryResponse>?) {
        if (response?.size!! > 0) {

        }
    }

    private fun onCountryResponseError(error: Throwable) {
        Log.d(TAG, error.message)
    }

}

