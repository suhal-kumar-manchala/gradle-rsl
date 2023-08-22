package com.andorid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andorid.domain.use_case.GetCountriesUseCase
import com.andorid.common_utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val getcountriesUseCase: GetCountriesUseCase) :
    ViewModel() {

    private val _countries = MutableStateFlow(CountriesState())
    val countries: StateFlow<CountriesState> = _countries


    fun getCountriesArticles() {
        getcountriesUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _countries.value = CountriesState(isLoading = true)
                }
                is Resource.Error -> {
                    _countries.value = CountriesState(error = it.message)
                }
                is Resource.Success -> {
                    _countries.value = CountriesState(data = it.data)
                }
            }


        }.launchIn(viewModelScope)
    }

}