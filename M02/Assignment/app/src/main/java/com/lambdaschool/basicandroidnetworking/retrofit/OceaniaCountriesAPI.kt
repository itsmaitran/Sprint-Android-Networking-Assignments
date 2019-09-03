package com.lambdaschool.basicandroidnetworking.retrofit

import com.lambdaschool.basicandroidnetworking.model.OceaniaCountryList
import retrofit2.Call
import retrofit2.http.GET

interface OceaniaCountriesAPI {

    @GET("oceania")
    fun getCountries(): Call<OceaniaCountryList>
}