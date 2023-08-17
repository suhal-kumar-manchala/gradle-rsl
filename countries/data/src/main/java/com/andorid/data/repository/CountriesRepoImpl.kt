package com.andorid.data.repository

import com.andorid.data.mapper.toDomainCountry
import com.andorid.data.network.CountriesApiService
import com.andorid.domain.model.Country
import com.andorid.domain.repository.CountriesRepository

class CountriesRepoImpl(private val countriesApiService: CountriesApiService) :
    CountriesRepository {

    override suspend fun getCountries(): List<Country> {
        val list = countriesApiService.getCountries().map {
            it.toDomainCountry()
        }
        return list
    }
}