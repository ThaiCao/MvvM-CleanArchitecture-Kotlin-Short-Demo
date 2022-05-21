package com.example.mydemo.local.sharedPreferences.movie

import android.content.SharedPreferences
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.local.sharedPreferences.reference

class MovieSharedPreferenceDataStoreImpl(prefs: SharedPreferences) : MovieSharedPreferenceDataStore {

    private var expiredTime: Long? by prefs.reference(PREF_MOVIE_EXPIRED_TIME)

    companion object {
        const val PREF_MOVIE_EXPIRED_TIME = "pref_movie_expired_time"
    }

    override fun saveMovieCacheExpiredTime(expiredTime: Long) {
        this.expiredTime = expiredTime
    }

    override fun getMovieCacheExpiredTime(): Long? {
        return expiredTime
    }
}