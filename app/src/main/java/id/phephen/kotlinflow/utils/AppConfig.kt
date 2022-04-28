package id.phephen.kotlinflow.utils

import com.google.gson.GsonBuilder
import id.phephen.kotlinflow.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Phephen on 25/03/2022.
 */
object AppConfig {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun ApiService(): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)

}