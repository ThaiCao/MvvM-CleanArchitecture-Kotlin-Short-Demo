package com.example.structure.presentation.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.structure.domain.feature.home.GetHomeHotUseCase
import com.example.structure.domain.feature.home.GetHomeMenuUseCase
import com.example.structure.domain.feature.home.GetHomeNewUseCase
import com.example.structure.domain.model.HomeMenu
import com.example.structure.presentation.base.BaseViewModel
import com.example.structure.presentation.error.ErrorMessageHandler
import com.example.structure.presentation.mapper.HomeMenuUiMapper
import com.example.structure.presentation.model.ErrorUi
import com.example.structure.presentation.model.HomeMenuUi
import com.example.structure.presentation.util.SingleLiveEvent

class HomeViewModel(
    private val getHomeHotUseCase: GetHomeHotUseCase,
    private val getHomeNewUseCase: GetHomeNewUseCase,
    private val getHomeMenuUseCase: GetHomeMenuUseCase,
    private val errorMessageHandler: ErrorMessageHandler,
    private val homeMenuUiMapper: HomeMenuUiMapper,
) : BaseViewModel() {

    val onHomeMenuError = SingleLiveEvent<String>()
    val homeMenus = MutableLiveData<List<HomeMenuUi>>()

    fun getHomeMenu(apiKey: String) {
        loading.value = true
        getHomeMenuUseCase(
            viewModelScope,
            GetHomeMenuUseCase.Params(
                apiKey = apiKey
            )
        ) {result ->
            loading.value = false
            result.fold(onSuccess = {
                homeMenus.value = toHomeMenuUis(it)
            }, onFailure = { error ->
                when (val errorUi = errorMessageHandler.resolve(error)) {
                    is ErrorUi.Message -> onHomeMenuError.value = errorUi.message
                    else -> handleError(error)
                }
            })
        }
    }

    private fun toHomeMenuUis(homeMenus: List<HomeMenu>): List<HomeMenuUi> {
        return homeMenus.map {
            homeMenuUiMapper.toHomeMenuUi(it)
        }.toList()
    }
}
