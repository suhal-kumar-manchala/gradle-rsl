package com.andorid.domain.di

import com.andorid.domain.repository.CountriesRepository
import com.andorid.domain.use_case.GetCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {


    @Provides
    fun provideCountriesUseCase(countriesRepository: CountriesRepository): GetCountriesUseCase {
        return GetCountriesUseCase(countriesRepository)
    }


}