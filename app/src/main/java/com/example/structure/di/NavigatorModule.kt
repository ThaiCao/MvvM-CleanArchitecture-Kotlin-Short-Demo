package com.example.structure.di

import com.example.structure.feature.detail.MovieDetailNavigator
import com.example.structure.feature.detail.MovieDetailNavigatorImpl
import com.example.structure.feature.home.HomeNavigator
import com.example.structure.feature.home.HomeNavigatorImpl
import com.example.structure.feature.signin.SignInNavigator
import com.example.structure.feature.signin.SignInNavigatorImpl
import com.example.structure.feature.splash.SplashNavigator
import com.example.structure.feature.splash.SplashNavigatorImpl
import org.koin.dsl.module

val navigatorModule = module {

    single<SplashNavigator> { SplashNavigatorImpl() }

    single<HomeNavigator> { HomeNavigatorImpl() }

    single<SignInNavigator> { SignInNavigatorImpl() }

    single<MovieDetailNavigator> { MovieDetailNavigatorImpl() }
}
