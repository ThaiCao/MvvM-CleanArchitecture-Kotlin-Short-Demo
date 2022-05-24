package com.example.mydemo.features.main

import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mydemo.R
import com.example.mydemo.base.activity.BaseActivity
import com.example.mydemo.databinding.ActivityMainBinding
import com.example.mydemo.features.popularmovie.PopularMovieNavigator
import com.example.mydemo.utils.common.viewBinding
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    val binding by viewBinding(ActivityMainBinding::bind)

    private lateinit var navController: NavController
    private val popularMovieNavigator: PopularMovieNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
//        setSupportActionBar(binding.toolbar)
        setupNavigator()
    }

    private fun getNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private fun setupNavigator() {
        navController = getNavHostFragment().navController
        popularMovieNavigator.bind(navController = navController)
    }
}
