package com.rezkyatinnov.jeniuscontact

import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rezkyatinnov.jeniuscontact.injection.AppModule
import com.rezkyatinnov.jeniuscontact.injection.DaggerAppComponent
import com.rezkyatinnov.jeniuscontact.restapi.RestapiModule
import com.rezkyatinnov.jeniuscontact.ui.main.MainActivity
import com.rezkyatinnov.jeniuscontact.ui.main.MainViewModel
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Mock
    var mainActivity: MainActivity = MainActivity()

    @Mock
    lateinit var mockLiveDataObserver: Observer<Boolean>

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(mainActivity)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Given DataRepository returns data, when loadAllContact() called, then update live data`() {
        //Setting how up the mock behaves
//        whenever(mockDataRepository.fetchData()).thenReturn(Observable.just("Data"))
//        whenever(mainViewModel.activity.appComponent).thenReturn(DaggerAppComponent.builder().appModule(AppModule(mainActivity)).restapiModule(RestapiModule(mainActivity)).build())

        //Fire the test method
        mainViewModel.loadAllContact()

        //Check that our live data is updated
        Assert.assertEquals(true, mainViewModel.isRefresh.value)
    }

    @Test
    fun `Given DataRepository returns error, when getStuff() called, then do not change live data`() {
        //Setting how up the mock behaves
//        whenever(mockDataRepository.fetchData()).thenReturn(Observable.error(Throwable()))
//        whenever(mainViewModel.activity.appComponent).thenReturn(DaggerAppComponent.builder().appModule(AppModule(mainActivity)).restapiModule(RestapiModule(mainActivity)).build())


        mainViewModel.isRefresh.observeForever(mockLiveDataObserver)

        //Fire the test method
        mainViewModel.loadAllContact()

        verify(mockLiveDataObserver, times(0)).onChanged(any())
    }
}