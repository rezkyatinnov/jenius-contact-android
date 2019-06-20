package com.rezkyatinnov.jeniuscontact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.restapi.ApiResponse
import com.rezkyatinnov.jeniuscontact.restapi.ApiServices
import com.rezkyatinnov.jeniuscontact.ui.main.MainActivity
import com.rezkyatinnov.jeniuscontact.ui.main.MainViewModel
import com.rezkyatinnov.jeniuscontact.utils.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import retrofit2.Response
import java.util.concurrent.TimeUnit


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

    var contact = Contact()
    @Mock
    lateinit var mockIsRefreshObserver: Observer<Boolean>

    private val testScheduler = TestScheduler()
    private var testSchedulerProvider = TestSchedulerProvider(testScheduler)

    @Before
    fun setUp() {
        contact.firstName = "John"
        contact.lastName = "Doe"
        contact.age = 27
        contact.photo = "https://image.flaticon.com/icons/svg/145/145843.svg"

        MockitoAnnotations.initMocks(this)
        val controller = Robolectric.buildActivity(MainActivity::class.java).create().start().resume()
        mainActivity = controller.get()
        mainViewModel = MainViewModel(mainActivity)
        mainViewModel.apiServices = Mockito.mock(ApiServices::class.java)
        mainViewModel.schedulerProvider = testSchedulerProvider
    }

    @After
    fun tearDown() {
    }

    @Test
    fun mainViewModelLoadAllContactTest() {
        val mockApiResponse = ApiResponse<ArrayList<Contact>>()
        mockApiResponse.message = "success"
        mockApiResponse.data = ArrayList()
        mockApiResponse.data?.add(contact)
        val mockResponse = Response.success(mockApiResponse)
        val observable = Observable.just(mockResponse)

//        val testObserver = TestObserver<Response<ApiResponse<Contact>>>(detailViewModel)
        observable.doOnSubscribe { mainViewModel.onRestCallStart() }
            .doOnTerminate { mainViewModel.onRestCallFinish() }
            .subscribe(
                { result -> mainViewModel.onRestCallSuccess(result) },
                { throwable -> mainViewModel.onRestCallError(throwable) }
            )

        BDDMockito.given(mainViewModel.apiServices.getContacts()).willReturn(observable)

        mainViewModel.loadAllContact()

        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)

        Assert.assertEquals(mockResponse.body()?.data, mainViewModel.mainListAdapter.data)
    }

    @Test
    fun mainViewModelLoadAllContactSuccessMethodCallTest() {
        val mockApiResponse = ApiResponse<ArrayList<Contact>>()
        mockApiResponse.message = "success"
        mockApiResponse.data = ArrayList()
        mockApiResponse.data?.add(contact)
        val mockResponse = Response.success(mockApiResponse)
        val observable = Observable.just(mockResponse)

        mainViewModel.isRefresh.observeForever(mockIsRefreshObserver)
        observable.doOnSubscribe {
            mainViewModel.onRestCallStart()
            Assert.assertEquals(true, mainViewModel.isRefresh.value)
        }.doOnTerminate {
            mainViewModel.onRestCallFinish()
            Assert.assertEquals(false, mainViewModel.isRefresh.value)
        }.subscribe({ result ->
            mainViewModel.onRestCallSuccess(result)
            Assert.assertEquals(false, mainViewModel.isRefresh.value)
        }, { throwable ->
            mainViewModel.onRestCallError(throwable)
            Assert.assertEquals(false, mainViewModel.isRefresh.value)
        })

        BDDMockito.given(mainViewModel.apiServices.getContacts()).willReturn(observable)

        mainViewModel.loadAllContact()

        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)

        verify(mockIsRefreshObserver, times(6)).onChanged(any())
    }

    @Test
    fun mainViewModelLoadAllContactErrorMethodCallTest() {
        val mockResponse = Response.error<ApiResponse<ArrayList<Contact>>>(
            404,
            ResponseBody.create(MediaType.get("application/json"),"{\"message\":\"no data\"}")
        )
        val observable = Observable.just(mockResponse)

        mainViewModel.isRefresh.observeForever(mockIsRefreshObserver)
        observable.doOnSubscribe {
            mainViewModel.onRestCallStart()
            Assert.assertEquals(true, mainViewModel.isRefresh.value)
        }.doOnTerminate {
            mainViewModel.onRestCallFinish()
            Assert.assertEquals(false, mainViewModel.isRefresh.value)
        }.subscribe({ result ->
            mainViewModel.onRestCallSuccess(result)
            Assert.assertEquals(false, mainViewModel.isRefresh.value)
        }, { throwable ->
            mainViewModel.onRestCallError(throwable)
            Assert.assertEquals(false, mainViewModel.isRefresh.value)
        })

        BDDMockito.given(mainViewModel.apiServices.getContacts()).willReturn(observable)

        mainViewModel.loadAllContact()

        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)

        verify(mockIsRefreshObserver, times(6)).onChanged(any())
    }
}