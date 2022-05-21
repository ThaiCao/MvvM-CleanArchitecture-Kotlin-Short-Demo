package com.example.mydemo.data.datasource.movie.local.sharedpreference

interface MovieSharedPreferenceDataStore {
    fun saveMovieCacheExpiredTime(expiredTime: Long)
    fun getMovieCacheExpiredTime(): Long?
}