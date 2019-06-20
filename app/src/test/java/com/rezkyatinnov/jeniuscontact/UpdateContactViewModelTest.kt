package com.rezkyatinnov.jeniuscontact

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.restapi.ApiResponse
import com.rezkyatinnov.jeniuscontact.restapi.ApiServices
import com.rezkyatinnov.jeniuscontact.ui.createupdatecontact.CreateUpdateContactActivity
import com.rezkyatinnov.jeniuscontact.ui.createupdatecontact.CreateUpdateContactViewModel
import com.rezkyatinnov.jeniuscontact.utils.TestSchedulerProvider
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
class UpdateContactViewModelTest {

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

    private val testScheduler = TestScheduler()
    private var testSchedulerProvider = TestSchedulerProvider(testScheduler)

    @Before
    fun setUp() {
        contact.firstName = "John"
        contact.lastName = "Doe"
        contact.age = 27
        contact.photo = "https://image.flaticon.com/icons/svg/145/145843.svg"

        MockitoAnnotations.initMocks(this)
        val controller = Robolectric.buildActivity(CreateUpdateContactActivity::class.java).create().start().resume()
        createUpdateContactActivity = controller.get()
        createUpdateContactActivity.id = "93ad6070-c92b-11e8-b02f-cbfa15db428b"
        createUpdateContactActivity.isUpdate = true

        createUpdateContactViewModel = CreateUpdateContactViewModel(createUpdateContactActivity)
        createUpdateContactViewModel.apiServices = Mockito.mock(ApiServices::class.java)
        createUpdateContactViewModel.schedulerProvider = testSchedulerProvider

        createUpdateContactViewModel.contact.firstName = contact.firstName
        createUpdateContactViewModel.contact.lastName = contact.lastName
        createUpdateContactViewModel.contact.photo = contact.photo
        createUpdateContactViewModel.contact.age = contact.age
    }

    @After
    fun tearDown() {
    }

    @Test
    fun updateContactViewModelSaveContactTest() {
        val mockApiResponse = ApiResponse<Contact>()
        mockApiResponse.message = "success"
        mockApiResponse.data = contact
        val mockResponse = Response.success(mockApiResponse)
        val observable = Observable.just(mockResponse)

        observable.doOnSubscribe {
            createUpdateContactViewModel.updateRestSubscriber.onRestCallStart()
            Assert.assertEquals(View.VISIBLE, createUpdateContactViewModel.loadingVisibility.value)
        }.doOnTerminate {
            createUpdateContactViewModel.updateRestSubscriber.onRestCallFinish()
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        }.subscribe({ result ->
            createUpdateContactViewModel.updateRestSubscriber.onRestCallSuccess(result)
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        }, { throwable ->
            createUpdateContactViewModel.updateRestSubscriber.onRestCallError(throwable)
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        })

        BDDMockito.`when`(createUpdateContactViewModel.apiServices.putContact(createUpdateContactActivity.id, createUpdateContactViewModel.contact)).thenReturn(observable)

        createUpdateContactViewModel.saveContact()
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
    }

    @Test
    fun createUpdateViewModelPreloadTest(){
        val mockApiResponse = ApiResponse<Contact>()
        mockApiResponse.message = "success"
        mockApiResponse.data = contact
        val mockResponse = Response.success(mockApiResponse)
        val observable = Observable.just(mockResponse)

        observable.doOnSubscribe {
            createUpdateContactViewModel.preloadRestSubscriber.onRestCallStart()
            Assert.assertEquals(View.VISIBLE, createUpdateContactViewModel.loadingVisibility.value)
        }.doOnTerminate {
            createUpdateContactViewModel.preloadRestSubscriber.onRestCallFinish()
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        }.subscribe({ result ->
            createUpdateContactViewModel.preloadRestSubscriber.onRestCallSuccess(result)
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
            Assert.assertEquals("27", createUpdateContactViewModel.ageMutable.value)
            Assert.assertEquals(contact.firstName, createUpdateContactViewModel.firstnameMutable.value)
            Assert.assertEquals(contact.lastName, createUpdateContactViewModel.lastnameMutable.value)
            Assert.assertEquals(contact.photo, createUpdateContactViewModel.avatarMutable.value)
        }, { throwable ->
            createUpdateContactViewModel.preloadRestSubscriber.onRestCallError(throwable)
            Assert.assertEquals(View.GONE, createUpdateContactViewModel.loadingVisibility.value)
        })

        BDDMockito.given(createUpdateContactViewModel.apiServices.getContact(createUpdateContactActivity.id)).willReturn(observable)
        createUpdateContactViewModel.preloadContactData(createUpdateContactActivity.id)
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
    }
}