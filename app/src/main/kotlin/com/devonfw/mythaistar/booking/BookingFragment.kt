package com.devonfw.mythaistar.booking

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.widget.Toast
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.common.bindView
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.home.HomeFragment
import com.devonfw.mythaistar.main.MainComponent
import com.devonfw.mythaistar.view.SimpleOnTabSelectedListener
import com.devonfw.mythaistar.view.SlideViewSwitcher
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import com.devonfw.mythaistar.booking.data.BookForm
import com.devonfw.mythaistar.booking.mvp.BookingPresenterImpl
import com.devonfw.mythaistar.booking.mvp.BookingUiImpl
import com.devonfw.mythaistar.view.LinkedCheckBox
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*


class BookingFragment : BaseFragment<MainComponent, BookingUiImpl, BookingPresenterImpl>(),
        BookingUiImpl,
        View.OnClickListener,
        BookingConfirmDialog.BookingConfirmationDialogListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    override lateinit var getEtDateAndTime: EditText
    override lateinit var getEtName: EditText
    override lateinit var getEtEmail: EditText
    override lateinit var getCheckBoxAcceptTerms: LinkedCheckBox

    private val tabLayout by bindView<TabLayout>(R.id.tab_layout)
    private val viewSwitcher by bindView<SlideViewSwitcher>(R.id.view_switcher)
    private val viewInviteFriends by bindView<InviteFriendsView>(R.id.view_invite_friends)
    private val viewBookTable by bindView<BookTableView>(R.id.view_book_table)
    private lateinit var friendsEmail: String
    private var tmpDateForPayload: String? = null
    private var tmpDateForEt: String? = null

    private var tmpBookForm: BookForm? = null

    override val layoutId = R.layout.fragment_booking
    override val ui = this
    override fun onViewCreated(view: View, inState: Bundle?) {
        super.onViewCreated(view, inState)
        viewBookTable.getBtnBookTable.setOnClickListener(this)
        viewInviteFriends.getBtnInviteFriends.setOnClickListener(this)

        getEtDateAndTime = viewBookTable.getEtDateAndTime
        getEtDateAndTime.setOnClickListener { showDatePickerDialog()}

        tabLayout.addOnTabSelectedListener(object : SimpleOnTabSelectedListener() {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    POSITION_BOOK_TABLE -> viewSwitcher.withSlideToLeft().showNext()
                    POSITION_INVITE_FRIENDS -> viewSwitcher.withSlideToRight().showNext()
                }
            }
        })
    }

    /**
     * handle Onclick Event and call corresponding
     * Method from presenter
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_book_table -> {
                var bookForm = BookForm()
                getEtDateAndTime = viewBookTable.getEtDateAndTime
                getEtName = viewBookTable.getEtName
                getEtEmail = viewBookTable.getEtEmail
                getCheckBoxAcceptTerms = viewBookTable.getCheckBoxAcceptTerms
                bookForm.dateAndTime = getEtDateAndTime.text.toString()
                bookForm.name = getEtName.text.toString()
                bookForm.email = getEtEmail.text.toString().trim()
                bookForm.guests = viewBookTable.getNpGuests
                bookForm.isChecked = getCheckBoxAcceptTerms.checkBox.isChecked
                presenter.checkInfoForBookTable(bookForm)
            }
            R.id.btn_invite_friends -> {
                getEtDateAndTime = viewInviteFriends.getEtDateAndTime
                getEtName = viewInviteFriends.getEtName
                getEtEmail = viewInviteFriends.getEtEmail
                getCheckBoxAcceptTerms = viewInviteFriends.getCheckBoxAcceptTerms
                val dateAndTime = getEtDateAndTime.text.toString()
                val name = getEtName.text.toString()
                val email = getEtEmail.text.toString()
                friendsEmail = viewInviteFriends.getEtFriends.text.toString()
                val isChecked = getCheckBoxAcceptTerms.checkBox.isChecked
                presenter.checkInfoForInviteFriends(dateAndTime, name, email, friendsEmail, isChecked)

            }
        }
    }

    override fun onInviteFriendsDateTimeError() {
        viewInviteFriends.getEtDateAndTime.error = "This field can't be blank"
    }

    override fun onInviteFriendsNameError() {
        viewInviteFriends.getEtName.error = "This field can't be blank"
    }

    override fun onInviteFriendsEmailError() {
        viewInviteFriends.getEtEmail.error = "Wrong Type for Email"
    }

    override fun onInviteFriendsEmptyError() {
        viewInviteFriends.getEtFriends.error = "This field can't be blank"
    }

    override fun onInviteFriendsCheckBoxCheckedError() {
        viewInviteFriends.getCheckBoxAcceptTerms.checkBox.error = ""
    }

    override fun onInviteFriendsEmailsError() {
        viewInviteFriends.getEtFriends.error = "Wrong type of email address was detected"
    }

    private fun showBookingConfirmDialog(bookForm: BookForm) {
        val fragmentManager = fragmentManager
        val bookingConfirmDialog = BookingConfirmDialog()
        var args = Bundle()
        args.putString("name", bookForm.name)
        args.putString("date", bookForm.dateAndTime)
        args.putString("email", bookForm.email)
        args.putInt("guests", bookForm.guests)
        tmpBookForm = bookForm
        bookingConfirmDialog.setTargetFragment(this, 1)
        bookingConfirmDialog.arguments = args
        bookingConfirmDialog.show(fragmentManager, "TAG")
    }

    private fun showDatePickerDialog() {
        val now = Calendar.getInstance()
        var dpd: DatePickerDialog
        dpd = DatePickerDialog.newInstance(this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH))
        dpd.setCancelText(R.string.cancel)
        dpd.minDate = now
        dpd.show(fragmentManager, "date_picker")
    }

    private fun showTimePickerDialog() {
        val now = Calendar.getInstance()
        var tpd: TimePickerDialog
        tpd = TimePickerDialog.newInstance(this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), true)
        tpd.setCancelText(R.string.cancel)
        tpd.show(fragmentManager, "time_picker")
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val hour = String.format("%02d", hourOfDay)
        val minuteFormatted = String.format("%02d", minute)
        getEtDateAndTime.setText("$hour:$minuteFormatted on "+tmpDateForEt, TextView.BufferType.EDITABLE)
        tmpDateForPayload += "T$hourOfDay:$minute:00.000Z"
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val months = arrayOf("Jan","Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec")
        val actualMonth = months[monthOfYear]
        tmpDateForEt = "$actualMonth $dayOfMonth, $year"
        val month = monthOfYear + 1
        tmpDateForPayload = "$year-$month-$dayOfMonth"
        showTimePickerDialog()
    }

    /**
     * Book Table
     */
    override fun onCorrectInput(bookForm: BookForm) {
        showBookingConfirmDialog(bookForm)
    }

    override fun onConfirmBooking() {
        tmpBookForm?.dateAndTime = this.tmpDateForPayload!!
        presenter.startBookTableCall(retrofit, tmpBookForm!!)
        tmpBookForm = null
    }

    override fun onBookingResult(orderId: Int?, isOrdered: Boolean) {
        when {
            isOrdered && orderId != null -> {
                Toast.makeText(activity.applicationContext, "Nice job, orderId: $orderId", Toast.LENGTH_SHORT).show()
                navigator.show(HomeFragment())
            }
            else -> Toast.makeText(activity.applicationContext, getString(R.string.connection_problem), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Invite Friend
     */
    override fun onInviteFriends() {
        sendEmail(friendsEmail)
        navigator.show(HomeFragment())
    }

    private fun sendEmail(email: String?) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        val emailsArry = email?.split(" ")?.toTypedArray()
        i.putExtra(Intent.EXTRA_EMAIL, emailsArry)
        i.putExtra(Intent.EXTRA_SUBJECT, "Invitation ")
        i.putExtra(Intent.EXTRA_TEXT, "I invite you to Restaurant")

        try {
            startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(activity.applicationContext, R.string.no_email_installed, Toast.LENGTH_SHORT).show()
        }
    }

    override fun handleBackButton(): Boolean {
        navigator.show(HomeFragment())
        return true
    }

    override fun inject(component: MainComponent) {
        component.inject(this)
    }

    companion object {
        private const val POSITION_INVITE_FRIENDS = 0
        private const val POSITION_BOOK_TABLE = 1
        const val URL_TERMS = "https://www.example.com"
    }
}
