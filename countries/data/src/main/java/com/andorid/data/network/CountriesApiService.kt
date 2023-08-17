package com.andorid.data.network

import com.andorid.data.model.Countries
import retrofit2.http.GET

interface CountriesApiService {

    // https://restcountries.com/v3.1/all
    @GET("all")
    suspend fun getCountries(): Countries

}