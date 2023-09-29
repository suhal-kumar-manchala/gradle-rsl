package com.andorid.presentation

import com.andorid.domain.use_case.GetCountriesUseCase
import com.andorid.common_utils.Resource
import com.andorid.domain.model.Country
import com.andorid.presentation.CountriesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CountriesViewModelTest {

    private lateinit var viewModel: CountriesViewModel

    // Mock the GetCountriesUseCase
    @Mock
    private lateinit var getCountriesUseCase: GetCountriesUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = CountriesViewModel(getCountriesUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun success() = runTest {
        // Arrange
        val countriesData = listOf(
            Country("Country 1"),
            Country("Country 2")
        )
        val successResource = Resource.Success(countriesData)
        `when`(getCountriesUseCase.invoke()).thenReturn(flowOf(successResource))
        viewModel.getCountriesArticles()
        testDispatcher.scheduler.advanceUntilIdle()
        // Assert
        val currentState = viewModel.countries.value
        assertEquals(countriesData, currentState.data)
    }
}
