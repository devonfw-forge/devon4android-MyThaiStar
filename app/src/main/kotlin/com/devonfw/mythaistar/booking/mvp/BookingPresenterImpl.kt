package com.devonfw.mythaistar.booking.mvp


import com.devonfw.mythaistar.booking.BookingPresenter
import com.devonfw.mythaistar.booking.api.models.BookingResponse
import com.devonfw.mythaistar.booking.api.BookingClient
import com.devonfw.mythaistar.booking.api.models.BookingRequest
import com.devonfw.mythaistar.booking.api.models.BookingRequestDetails
import com.devonfw.mythaistar.booking.data.BookForm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Created by MGWIZDAL on 2018-03-12.
 */
class BookingPresenterImpl @Inject constructor() : BookingPresenter<BookingUiImpl>() {
    fun checkInfoForBookTable(bookForm: BookForm) {
        val isInputCorrect = isBookFormCorrect(isCorrectDateTime(bookForm.dateAndTime), isCorrectName(bookForm.name), isCorrectEmail(bookForm.email), bookForm.isChecked)
        if (isInputCorrect) view?.onCorrectInput(bookForm)
    }

    private fun isBookFormCorrect(corrrectDateAndTimeVar: Boolean,
                            correctNameVar: Boolean,
                            corrrectEmailVar: Boolean,
                            checked: Boolean): Boolean {
        return when {
            !corrrectDateAndTimeVar -> onBookTableDateTimeError()
            !correctNameVar -> onBookTableNameError()
            !corrrectEmailVar -> onBookTableEmailError()
            !checked -> onBookTableCheckBoxCheckedError()
            else -> true
        }
    }

    fun startBookTableCall(retrofit: Retrofit, bookForm: BookForm) {
        val bookingResponseCall: Call<BookingResponse>
        val bookingRequestDetails = BookingRequestDetails(bookForm.dateAndTime, bookForm.name, bookForm.email, bookForm.guests)
        val bookingRequest = BookingRequest(bookingRequestDetails)
        bookingResponseCall = retrofit.create<BookingClient>(BookingClient::class.java).postBooking(bookingRequest)

        bookingResponseCall.enqueue(object : Callback<BookingResponse> {
            override fun onResponse(call: Call<BookingResponse>?, response: Response<BookingResponse>?) {
                val orderId = response?.body()?.id
                view?.onBookingResult(orderId, true)
            }
            override fun onFailure(call: Call<BookingResponse>?, t: Throwable?) {
                view?.onBookingResult()
            }
        })
    }

    private fun onBookTableDateTimeError(): Boolean {
        view?.getEtDateAndTime?.error = "This field can't be blank"
        return false
    }

    private fun onBookTableNameError(): Boolean {
        view?.getEtName?.error = "This field can't be blank"
        return false
    }

    private fun onBookTableEmailError(): Boolean {
        view?.getEtEmail?.error = "Wrong Type for Email"
        return false
    }

    private fun onBookTableCheckBoxCheckedError(): Boolean {
        view?.getCheckBoxAcceptTerms?.checkBox?.error = ""
        return false
    }

    fun checkInfoForInviteFriends(dateAndTime: String, name: String,
                                  email: String, friends: String, checked: Boolean) {

        when {
            !isCorrectDateTime(dateAndTime) -> view?.onInviteFriendsDateTimeError()
            !isCorrectName(name) -> view?.onInviteFriendsNameError()
            !isCorrectEmail(email) -> view?.onInviteFriendsEmailError()
            !isFriendsEtEmpty(friends) -> view?.onInviteFriendsEmptyError()
            !isCorrectFriendsEmails(friends) -> view?.onInviteFriendsEmailsError()
            !checked -> view?.onInviteFriendsCheckBoxCheckedError()
            else -> view?.onInviteFriends()
        }

    }


    private fun isCorrectDateTime(dateTime: String): Boolean {
        return !dateTime.isEmpty()
    }

    private fun isCorrectName(name: String): Boolean {
        return !name.isEmpty()
    }

    private fun isCorrectEmail(emailStr: String): Boolean {
        val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
        return matcher.find()
    }

    private val VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

    private fun isFriendsEtEmpty(friends: String): Boolean {
        return !friends.equals("")
    }

    private fun isCorrectFriendsEmails(emails: String): Boolean {
        val emailsArray = emails.split(" ")
        for (email in emailsArray) {
            if (!isCorrectEmail(email)) {
                return false
            }
        }
        return true
    }

}


