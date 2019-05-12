package hu.bme.aut.movieapp

import hu.bme.aut.movieapp.mock.MockDatabaseModule
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowLog

val testInjector: TestComponent
    get() {
        ShadowLog.stream = System.out
        val application = RuntimeEnvironment.application as MovieAppApplication
        val component =
            DaggerTestComponent.builder().mockDatabaseModule(MockDatabaseModule(application.applicationContext))
                .testModule(TestModule(application.applicationContext)).build()
        application.injector = component
        return component
    }