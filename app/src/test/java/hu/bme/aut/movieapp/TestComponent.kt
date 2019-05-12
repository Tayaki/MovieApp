package hu.bme.aut.movieapp

import dagger.Component
import hu.bme.aut.movieapp.interactor.InteractorModule
import hu.bme.aut.movieapp.mock.MockDatabaseModule
import hu.bme.aut.movieapp.mock.MockNetworkModule
import hu.bme.aut.movieapp.test.CreateTest
import hu.bme.aut.movieapp.test.DetailsTest
import hu.bme.aut.movieapp.test.MainTest
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class, MockDatabaseModule::class])
interface TestComponent : MovieAppApplicationComponent {
    fun inject(mainTest: MainTest)
    fun inject(detailsTest: DetailsTest)
    fun inject(createShowTest: CreateTest)
}