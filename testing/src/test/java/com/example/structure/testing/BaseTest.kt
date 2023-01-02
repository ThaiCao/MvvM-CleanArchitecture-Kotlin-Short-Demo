package com.example.structure.testing

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.context.stopKoin

@RunWith(JUnit4::class)
abstract class BaseTest : KoinComponent {
    private val di = KoinApplication.init()

    final override fun getKoin(): Koin {
        return di.koin
    }

    @Before
    open fun setUp() {
        stopKoin()
    }

    @After
    open fun tearDown() {
        stopKoin()
        di.close()
    }

}
