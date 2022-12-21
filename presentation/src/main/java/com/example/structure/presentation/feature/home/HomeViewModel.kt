package com.example.structure.presentation.feature.home

import androidx.lifecycle.viewModelScope
import com.example.structure.domain.feature.home.GetHomeHotUseCase
import com.example.structure.domain.feature.home.GetHomeMenuUseCase
import com.example.structure.domain.feature.home.GetHomeNewUseCase
import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.model.HotMenu
import com.example.structure.domain.model.NewMenu
import com.example.structure.presentation.base.BaseViewModel
import com.example.structure.presentation.error.ErrorMessageHandler
import com.example.structure.presentation.model.ErrorUi
import com.example.structure.presentation.model.HomeItemUi
import com.example.structure.presentation.util.SingleLiveEvent

class HomeViewModel(
    private val getHomeHotUseCase: GetHomeHotUseCase,
    private val getHomeNewUseCase: GetHomeNewUseCase,
    private val getHomeMenuUseCase: GetHomeMenuUseCase,
    private val errorMessageHandler: ErrorMessageHandler,
    private val homeMapper: HomeMapper,
) : BaseViewModel() {

    val onHomeMenuError = SingleLiveEvent<String>()
    val onShowHomeDetail = SingleLiveEvent<List<HomeItemUi>>()

    private var homeMenuItems: List<HomeMenu>? = null
    private var hotMenuItems: List<HotMenu>? = null
    private var newMenuItems: List<NewMenu>? = null

    fun getHomeHotMenu(apiKey: String) {
        loading.value = true
        getHomeHotUseCase(
            viewModelScope,
            GetHomeHotUseCase.Params(
                apiKey = apiKey
            )
        ) { result ->
            loading.value = false
            result.fold(onSuccess = {
//                homeMenus.value = toHomeMenuUis(it)
                android.util.Log.e("TEST_DATA", "getHomeHotMenu SUCCESS")
                hotMenuItems = it
                showHomeMenuScreen()
            }, onFailure = { error ->
                android.util.Log.e("TEST_DATA", "getHomeHotMenu error= $error")
                when (val errorUi = errorMessageHandler.resolve(error)) {
                    is ErrorUi.Message -> onHomeMenuError.value = errorUi.message
                    else -> handleError(error)
                }
            })
        }
    }

    fun getHomeNewMenu(apiKey: String) {
        loading.value = true
        getHomeNewUseCase(
            viewModelScope,
            GetHomeNewUseCase.Params(
                apiKey = apiKey
            )
        ) { result ->
            loading.value = false
            result.fold(onSuccess = {
//                homeMenus.value = toHomeMenuUis(it)
                android.util.Log.e("TEST_DATA", "getHomeNewMenu SUCCESS")
                newMenuItems = it
                showHomeMenuScreen()
            }, onFailure = { error ->
                android.util.Log.e("TEST_DATA", "getHomeNewMenu error= $error")
                when (val errorUi = errorMessageHandler.resolve(error)) {
                    is ErrorUi.Message -> onHomeMenuError.value = errorUi.message
                    else -> handleError(error)
                }
            })
        }
    }

    fun getHomeMenu(apiKey: String) {
        loading.value = true
        getHomeMenuUseCase(
            viewModelScope,
            GetHomeMenuUseCase.Params(
                apiKey = apiKey
            )
        ) { result ->
            loading.value = false
            result.fold(onSuccess = {
//                homeMenus.value = toHomeMenuUis(it)
                android.util.Log.e("TEST_DATA", "getHomeMenu SUCCESS")
                homeMenuItems = it
                showHomeMenuScreen()
            }, onFailure = { error ->
                android.util.Log.e("TEST_DATA", "getHomeMenu error= $error")
                when (val errorUi = errorMessageHandler.resolve(error)) {
                    is ErrorUi.Message -> onHomeMenuError.value = errorUi.message
                    else -> handleError(error)
                }
            })
        }
    }


    fun showHomeMenuScreen() {
        val cartDetailItems =
            homeMapper.toHomeDetailItems(
                homeMovie = homeMenuItems,
                newMovie = newMenuItems,
                hotMovie = hotMenuItems
            )
        onShowHomeDetail.value = cartDetailItems
    }
}
