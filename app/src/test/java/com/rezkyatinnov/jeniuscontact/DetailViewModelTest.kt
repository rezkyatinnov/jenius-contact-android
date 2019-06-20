package com.rezkyatinnov.jeniuscontact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rezkyatinnov.jeniuscontact.model.Contact
import com.rezkyatinnov.jeniuscontact.restapi.ApiResponse
import com.rezkyatinnov.jeniuscontact.restapi.ApiServices
import com.rezkyatinnov.jeniuscontact.ui.detail.DetailActivity
import com.rezkyatinnov.jeniuscontact.ui.detail.DetailViewModel
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
class DetailViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()

        @ClassRule
        @JvmField
        val ruleForLivaData = InstantTaskExecutorRule()
    }

    @Mock
    lateinit var detailActivity: DetailActivity

    private lateinit var detailViewModel: DetailViewModel

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
        val controller = Robolectric.buildActivity(DetailActivity::class.java).create().start().resume()
        detailActivity = controller.get()
        detailViewModel = DetailViewModel(detailActivity)
        detailViewModel.apiServices = Mockito.mock(ApiServices::class.java)
        detailViewModel.schedulerProvider = testSchedulerProvider
    }

    @After
    fun tearDown() {
    }

    @Test
    fun detailViewModelLoadContactDetailTest() {
        val mockApiResponse = ApiResponse<Contact>()
        mockApiResponse.message = "success"
        mockApiResponse.data = contact
        val mockResponse = Response.success(mockApiResponse)
        val observable = Observable.just(mockResponse)

//        val testObserver = TestObserver<Response<ApiResponse<Contact>>>(detailViewModel)
        observable.doOnSubscribe { detailViewModel.onRestCallStart() }
            .doOnTerminate { detailViewModel.onRestCallFinish() }
            .subscribe(
                { result -> detailViewModel.onRestCallSuccess(result) },
                { throwable -> detailViewModel.onRestCallError(throwable) }
            )

        BDDMockito.given(detailViewModel.apiServices.getContact("")).willReturn(observable)

        detailViewModel.loadContactDetail("")

        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)

        Assert.assertEquals(mockResponse.body()?.data?.firstName,detailViewModel.firstname.value)
        Assert.assertEquals(mockResponse.body()?.data?.lastName,detailViewModel.lastname.value)
        Assert.assertEquals(mockResponse.body()?.data?.photo,detailViewModel.avatar.value)
        Assert.assertEquals(mockResponse.body()?.data?.age.toString() + " yo",detailViewModel.age.value)
    }
}