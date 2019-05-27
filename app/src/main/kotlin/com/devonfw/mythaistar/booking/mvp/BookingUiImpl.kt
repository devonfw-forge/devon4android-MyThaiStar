package com.devonfw.mythaistar.booking.mvp

import android.widget.EditText
import com.devonfw.mythaistar.booking.BookingUi
import com.devonfw.mythaistar.booking.data.BookForm
import com.devonfw.mythaistar.view.LinkedCheckBox

/**
 * Created by MGWIZDAL on 2018-03-12.
 */
interface BookingUiImpl : BookingUi {
    var getEtDateAndTime : EditText
    var getEtName : EditText
    var getEtEmail : EditText
    var getCheckBoxAcceptTerms : LinkedCheckBox

    fun onCorrectInput(bookForm: BookForm)
    fun onBookingResult(orderId : Int? = 0, isOrdered: Boolean = false)


}