package com.andorid.domain.use_case

import com.andorid.domain.model.Country
import com.andorid.domain.repository.CountriesRepository
import com.andorid.common_utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


open class GetCountriesUseCase (private val countriesRepository: CountriesRepository) {

    open operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data=countriesRepository.getCountries()))
        }catch (e:Exception){
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}