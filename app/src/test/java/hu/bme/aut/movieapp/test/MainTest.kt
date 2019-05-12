package hu.bme.aut.movieapp.test

import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.testInjector
import hu.bme.aut.movieapp.ui.main.MainPresenter
import hu.bme.aut.movieapp.ui.main.MainScreen
import hu.bme.aut.movieapp.utils.argumentCaptor
import hu.bme.aut.movieapp.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import java.util.*
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class MainTest {
    @Inject
    lateinit var mainPresenter: MainPresenter
    private lateinit var mainScreen: MainScreen

    private val expectedShow = ShowDb(
        666, "Lucifer", Date(2016, 4, 25), "-", 8.2,  4, 67,
        "Best show ever!", "Crime, Drama, Fantasy", "Ongoing"
    )

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        mainScreen = mock()
        mainPresenter.attachScreen(mainScreen)
    }

    @Test
    fun testGetShows() {
        mainPresenter.getShows()
        val list = argumentCaptor<List<ShowDb>>()
        verify(mainScreen).listShows(list.capture())
        assert(list.value.isNotEmpty())
    }

    @Test
    fun testGetShowsWithExpectedValue() {
        val expectedShows = listOf(expectedShow, expectedShow, expectedShow)
        mainPresenter.getShows()
        verify(mainScreen).listShows(expectedShows)
    }

    @Test
    fun testRemoveShow() {
        mainPresenter.removeShow(expectedShow, 1)
        verify(mainScreen).removeShow(expectedShow, 1)
    }

    @After
    fun tearDown() {
        mainPresenter.detachScreen()
    }
}