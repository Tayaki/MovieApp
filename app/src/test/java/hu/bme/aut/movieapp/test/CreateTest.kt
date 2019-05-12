package hu.bme.aut.movieapp.test

import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.testInjector
import hu.bme.aut.movieapp.ui.create.CreatePresenter
import hu.bme.aut.movieapp.ui.create.CreateScreen
import hu.bme.aut.movieapp.ui.main.MainPresenter
import hu.bme.aut.movieapp.ui.main.MainScreen
import hu.bme.aut.movieapp.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import java.util.*
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class CreateTest {
    @Inject
    lateinit var createPresenter: CreatePresenter
    @Inject
    lateinit var mainPresenter: MainPresenter
    private lateinit var createScreen: CreateScreen
    private lateinit var mainScreen: MainScreen

    private val expectedShow = ShowDb(
        666, "Lucifer", Date(2016, 4, 25), "-", 8.2,  4, 67,
        "Best show ever!", "Crime, Drama, Fantasy", "Ongoing"
    )

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        createScreen = mock()
        mainScreen = mock()
        createPresenter.attachScreen(createScreen)
        mainPresenter.attachScreen(mainScreen)
    }

    @Test
    fun testAddShow() {
        createPresenter.addShow(expectedShow)
        Mockito.verify(createScreen).closeDialog()
        Mockito.verify(mainScreen).addShow(expectedShow)
    }

    @After
    fun tearDown() {
        createPresenter.detachScreen()
        mainPresenter.detachScreen()
    }
}