package com.ofir.heroapp.network

import com.ofir.heroapp.Constants.HEROAPPS_API_BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HeroappsApiService {
    @GET("/employee-tests/android/androidexam.json")
    fun getAllHeroesData(): Call<List<Model.Hero>>


    // Makes sure there is only one instance of retrofit at all time.
    companion object {
        fun create(): HeroappsApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HEROAPPS_API_BASE_URL)
                .build()
            return retrofit.create(HeroappsApiService::class.java)
        }
    }

}