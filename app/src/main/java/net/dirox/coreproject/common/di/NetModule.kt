package net.dirox.coreproject.common.di

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    companion object {
        private const val BASE_URL = "http://ec2-54-171-137-25.eu-west-1.compute.amazonaws.com:8080/yewou/api/"
    }

    @Singleton
    @Provides
    fun okhttp(application: Application): OkHttpClient {
        val httpLoggingIntercepter = HttpLoggingInterceptor()
        httpLoggingIntercepter.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newBuilder = request!!.newBuilder().addHeader("User-Agent", "Android")
                chain.proceed(newBuilder.build())
            }
            .addInterceptor(httpLoggingIntercepter)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val builder = Retrofit.Builder()
        builder.baseUrl(BASE_URL)
        return builder.addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}