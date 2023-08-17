package com.andorid.data.mapper

import com.andorid.data.model.CountryDTO
import com.andorid.domain.model.Country

fun CountryDTO.toDomainCountry(): Country {
    return Country(
        name = this.name.official,
    )
}