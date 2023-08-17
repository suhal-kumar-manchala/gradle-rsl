package com.andorid.data.di

import com.andorid.data.network.CountriesApiService
import com.andorid.data.repository.CountriesRepoImpl
import com.andorid.domain.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Provides
    fun provideApiService(retrofit: Retrofit): CountriesApiService {
        return retrofit.create(CountriesApiService::class.java)
    }


    @Provides
    fun provideCountriesRepository(apiService: CountriesApiService): CountriesRepository {
        return CountriesRepoImpl(apiService)
    }

}