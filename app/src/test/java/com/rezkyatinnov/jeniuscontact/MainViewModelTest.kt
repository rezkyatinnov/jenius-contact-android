package com.rezkyatinnov.jeniuscontact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rezkyatinnov.jeniuscontact.ui.main.MainActivity
import com.rezkyatinnov.jeniuscontact.ui.main.MainViewModel
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()

        @ClassRule
        @JvmField
        val ruleForLivaData = InstantTaskExecutorRule()
    }

    @Mock
    lateinit var mainActivity: MainActivity

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val controller = Robolectric.buildActivity(MainActivity::class.java).create().start().resume()
        mainActivity = controller.get()
        mainViewModel = MainViewModel(mainActivity)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when loadAllContact() called, then update isRefresh value`() {
        mainViewModel.loadAllContact()

        Assert.assertEquals(true, mainViewModel.isRefresh.value)
    }
}