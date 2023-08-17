package com.andorid.domain.repository

import com.andorid.domain.model.Country

interface CountriesRepository {
    suspend fun getCountries():List<Country>
}