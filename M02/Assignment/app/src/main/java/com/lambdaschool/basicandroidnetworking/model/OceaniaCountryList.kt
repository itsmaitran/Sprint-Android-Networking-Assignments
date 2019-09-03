package com.lambdaschool.basicandroidnetworking.model

data class OceaniCountryList (
    val country: List<OceaniaCountry>
)

data class OceaniaCountry (
    val name: String,
    val alpha2code: String,
    val capital: String,
    val subregion: String
)