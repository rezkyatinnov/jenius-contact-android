package com.rezkyatinnov.jeniuscontact

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.restapi.ApiResponse
import com.rezkyatinnov.jeniuscontact.restapi.ApiServices
import com.rezkyatinnov.jeniuscontact.ui.createupdatecontact.CreateUpdateContactActivity
import com.rezkyatinnov.jeniuscontact.ui.createupdatecontact.CreateUpdateContactViewModel
import com.rezkyatinnov.jeniuscontact.utils.TestSchedulerProvider
import com.rezkyatinnov.jeniuscontact.utils.TrampolineSchedulerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
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
class CreateContactViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()

        @ClassRule
        @JvmField
        val ruleForLivaData = InstantTaskExecutorRule()
    }

    @Mock
    lateinit var createUpdateContactActivity: CreateUpdateContactActivity

    private lateinit var createUpdateContactViewModel: CreateUpdateContactViewModel

    var contact = Contact()

    @Mock
    lateinit var mockVisibilityObserver: Observer<Int>

    private val testScheduler = TestScheduler()
    private var testSchedulerProvider = TestSchedulerProvider(testScheduler)
    private var schedulerProvider = TrampolineSchedulerProvider()

    @Before
    fun setUp() {
        contact.firstName = "John"
        contact.lastName = "Doe"
        contact.age = 27
        contact.photo = "https://image.flaticon.com/icons/svg/145/145843.svg"

        MockitoAnnotations.initMocks(this)
        val controller = Robolectric.buildActivity(CreateUpdateContactActivity::class.java).create().start().resume()
        createUpdateContactActivity = controller.get()
        createUpdateContactViewModel = CreateUpdateContactViewModel(createUpdateContactActivity)
        createUpdateContactViewModel.apiServices = Mockito.mock(ApiServices::class.java)
        createUpdateContactViewModel.schedulerProvider = schedulerProvider

        createUpdateContactViewModel.contact.firstName = contact.firstName
        createUpdateContactViewModel.contact.lastName = contact.lastName
        createUpdateContactViewModel.contact.photo = contact.photo
        createUpdateContactViewModel.contact.age = contact.age
    }

    @After
    fun tearDown() {
    }

    @Test
    fun createContactViewModelSaveContactTest() {
        val mockApiResponse = ApiResponse<Void>()
        mockApiResponse.message = "success"
        val mockResponse = Response.success(mockApiResponse)
        val observable = Observable.just(mockResponse)

        createUpdateContactViewModel.loadingVisibility.observeForever(mockVisibilityObserver)
        observable.doOnSubscribe {
            createUpdateContactViewModel.onRestCallStart()
            Assert.assertEquals(View.VISIBLE, createUpdateContactViewModel.loadingVisibility.value)
        }.doOnTerminate {
            createUpdateContactViewModel.onRestCallFinish()
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        }.subscribe({ result ->
            createUpdateContactViewModel.onRestCallSuccess(result)
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        }, { throwable ->
            createUpdateContactViewModel.onRestCallError(throwable)
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        })

        BDDMockito.given(createUpdateContactViewModel.apiServices.postContact(createUpdateContactViewModel.contact)).willReturn(observable)

        createUpdateContactViewModel.saveContact()

        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)

        verify(mockVisibilityObserver, times(7)).onChanged(any())
    }

    @Test
    fun createUpdateContactViewModelIsFormValidTest() {
        Assert.assertEquals(false,createUpdateContactViewModel.isFormValid())

        createUpdateContactViewModel.firstname = contact.firstName?:""
        createUpdateContactViewModel.lastname = contact.lastName?:""
        createUpdateContactViewModel.avatar.value = contact.photo
        createUpdateContactViewModel.age = contact.age?.toString()?:"27"

        Assert.assertEquals(true,createUpdateContactViewModel.isFormValid())
    }
}