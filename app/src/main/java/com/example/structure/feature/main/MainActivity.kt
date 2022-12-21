package com.example.structure.feature.main

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.structure.R
import com.example.structure.databinding.ActivityMainBinding
import com.example.structure.feature.detail.MovieDetailNavigator
import com.example.structure.feature.dialog.InternetConnectionFailBottomSheet
import com.example.structure.feature.home.HomeNavigator
import com.example.structure.feature.internetconnection.InternetConnectionHelper
import com.example.structure.feature.internetconnection.InternetConnectionListener
import com.example.structure.feature.signin.SignInNavigator
import com.example.structure.feature.splash.SplashNavigator
import com.example.structure.uibase.activity.BaseActivity
import com.example.structure.uibase.extend.showDialogFragment
import com.example.structure.uibase.extend.viewBinding
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    val binding by viewBinding(ActivityMainBinding::bind)

    private val splashNavigator: SplashNavigator by inject()
    private val homeNavigator: HomeNavigator by inject()
    private val signInNavigator: SignInNavigator by inject()
    private val movieDetailNavigator: MovieDetailNavigator by inject()


    private val internetConnectionHelper: InternetConnectionHelper by inject()
    private var internetConnectionFailBottomSheet: InternetConnectionFailBottomSheet? = null

    private lateinit var navController: NavController

    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigator()
    }

    private fun getNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private fun setupNavigator() {
        navController = getNavHostFragment().navController
        splashNavigator.bind(navController = navController)
        homeNavigator.bind(navController = navController)
        signInNavigator.bind(navController = navController)
        movieDetailNavigator.bind(navController = navController)
    }

    override fun onResume() {
        super.onResume()
        registerInternetConnection()
    }

    private fun registerInternetConnection() {
        internetConnectionHelper.registerInternetConnection(
            onAvailable = {
                runOnUiThread {
                    hideInternetConnectionDialog()
                    notifyHasNetworkListener()
                }
            },
            onLost = {
                runOnUiThread {
                    showInternetConnectionDialog()
                }
            }
        )
    }

    private fun hideInternetConnectionDialog() {
        internetConnectionFailBottomSheet?.dismiss()
    }

    private fun notifyHasNetworkListener() {
        getInternetConnectionListener().forEach {
            it.hasNetwork()
        }
    }

    private fun getInternetConnectionListener(): List<InternetConnectionListener> {
        val navHostFragment: NavHostFragment = supportFragmentManager.fragments
            .filterIsInstance<NavHostFragment>().firstOrNull() ?: return emptyList()
        if (navHostFragment.isAdded) {
            return navHostFragment.childFragmentManager.fragments.filterIsInstance<InternetConnectionListener>()
        }

        return emptyList()
    }

    private fun showInternetConnectionDialog() {
        val dialog =
            internetConnectionFailBottomSheet ?: InternetConnectionFailBottomSheet().apply {
                onDismissListener = {
                    notifyTryAgainListener()
                }
            }
        internetConnectionFailBottomSheet = dialog

        showDialogFragment(InternetConnectionFailBottomSheet.TAG) {
            dialog
        }
    }

    private fun notifyTryAgainListener() {
        getInternetConnectionListener().forEach {
            it.onTryAgainClicked()
        }
    }

    override fun onPause() {
        super.onPause()
        internetConnectionHelper.unregisterInternetConnection()
        hideInternetConnectionDialog()
    }
}
