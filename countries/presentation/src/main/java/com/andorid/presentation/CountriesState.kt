package com.andorid.presentation

import com.andorid.domain.model.Country

data class CountriesState(
    val isLoading:Boolean=false,
    val error:String="",
    val data:List<Country>?=null
)
