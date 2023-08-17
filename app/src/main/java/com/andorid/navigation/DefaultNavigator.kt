package com.andorid.navigation

import com.andorid.common_utils.Activities
import com.andorid.common_utils.Navigator
import com.andorid.presentation.GoToCountriesActivity


class DefaultNavigator : Navigator.Provider {

    override fun getActivities(activities: Activities): Navigator {
        return when (activities) {
            Activities.CountriesActivity -> {
                GoToCountriesActivity
            }
        }
    }
}