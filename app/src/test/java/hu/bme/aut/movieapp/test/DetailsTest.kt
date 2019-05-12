package hu.bme.aut.movieapp.test

import hu.bme.aut.movieapp.db.model.ShowDb
import hu.bme.aut.movieapp.testInjector
import hu.bme.aut.movieapp.ui.details.DetailsPresenter
import hu.bme.aut.movieapp.ui.details.DetailsScreen
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
class DetailsTest {
    @Inject
    lateinit var detailsPresenter: DetailsPresenter
    private lateinit var detailsScreen: DetailsScreen

    private val expectedShow = ShowDb(
        666, "Lucifer", Date(2016, 4, 25), "-", 8.2,  4, 67,
        "Best show ever!", "Crime, Drama, Fantasy", "Ongoing"
    )

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        detailsScreen = mock()
        detailsPresenter.attachScreen(detailsScreen)
    }

    @Test
    fun testGetShow() {
        detailsPresenter.getShow(555)
        verify(detailsScreen).showShow(expectedShow)
    }

    @After
    fun tearDown() {
        detailsPresenter.detachScreen()
    }
}