package com.example.structure.data.feature.home

import com.example.structure.mapper.feature.home.HomeMapper
import com.example.structure.model.data.HomeMenuDto
import com.example.structure.model.data.MovieMenuResponseDto
import com.example.structure.model.domain.HomeMenu
import com.example.structure.testing.BaseTest
import com.example.structure.testing.autoWire
import com.example.structure.testing.mock
import com.google.gson.annotations.SerializedName
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import java.math.BigDecimal

class HomeRepositoryImplTest : BaseTest() {


    private val homeService: HomeService = mock()
    private val homeMapper: HomeMapper = mock()
    private val repo: HomeRepositoryImpl = autoWire()

    @Test
    fun `get home menu success`() = runBlockingTest {
        // Given
        every { homeService.getHomeMenu(any()) } returns flow {
            emit(homeMenu)
        }

        every {
            homeMapper.toHomeMenuItems(listHomeMenuDto)
        } returns listHomeMenu


        // When
        var result: Result<List<HomeMenu>>? = null
        repo.getHomeMenu(apiKey).collect {
            result = it
        }

        // Then
        verify(exactly = 1) {
            homeService.getHomeMenu(
                apiKey = apiKey,
            )
        }

        verify(exactly = 1) {
            homeMapper.toHomeMenuItems(listHomeMenuDto)
        }

        MatcherAssert.assertThat(result!!.isSuccess, CoreMatchers.`is`(true))
        MatcherAssert.assertThat(
            result!!.getOrNull()!!,
            CoreMatchers.`is`(listHomeMenu)
        )
    }

    private val listHomeMenuDto: List<HomeMenuDto> = listOf(
        HomeMenuDto(
            id = 1,
            name = "Menu 1",
            imageUrl = "Image 1",
        ),
        HomeMenuDto(
            id = 2,
            name = "Menu 2",
            imageUrl = "Image 2",
        ),
        HomeMenuDto(
            id = 1,
            name = "Menu 3",
            imageUrl = "Image 3",
        )
    )

    private val listHomeMenu: List<HomeMenu> = listOf(
        HomeMenu(
            id = 1,
            name = "Menu 1",
            imageUrl = "Image 1",
        ),
        HomeMenu(
            id = 2,
            name = "Menu 2",
            imageUrl = "Image 2",
        ),
        HomeMenu(
            id = 1,
            name = "Menu 3",
            imageUrl = "Image 3",
        )
    )

private val homeMenu = MovieMenuResponseDto(
    page = 0,
    totalPage = 10,
    results = listHomeMenuDto
)

    private val apiKey = "apiKey"


}
