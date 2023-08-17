package com.andorid.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.andorid.common_utils.Navigator
import com.andorid.presentation.databinding.ActivityCountriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CountriesActivity : AppCompatActivity() {

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, CountriesActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var _binding: ActivityCountriesBinding? = null
    private val binding: ActivityCountriesBinding
        get() = _binding!!

    private val countriesViewModel: CountriesViewModel by viewModels()

    private val countriesAdapter = CountriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        setObservers()

    }

    private fun initView() {
        binding.rvArticles.adapter = countriesAdapter
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            countriesViewModel.countries.collectLatest {
                if (it.isLoading) {
                    binding.progressBar.visibility= View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility= View.GONE
                    Toast.makeText(this@CountriesActivity, it.error, Toast.LENGTH_LONG).show()
                }
                it.data?.let {
                    binding.progressBar.visibility= View.GONE
                    countriesAdapter.setData(it)
                }
            }
        }

    }
}

object GoToCountriesActivity: Navigator {
    override fun navigate(activity: Activity) {
        CountriesActivity.launchActivity(activity)
    }
}

