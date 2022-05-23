package com.example.mydemo.presentation.base

import org.junit.Rule
import com.example.mydemo.tooltest.BaseTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

abstract class PresentableTest : BaseTest() {

    @get:Rule
    val executorRule = ArchExecutorTestRule()

    val dispatcher = TestCoroutineDispatcher()

    @Before
    override fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    override fun tearDown() {
        Dispatchers.resetMain()
    }
}
