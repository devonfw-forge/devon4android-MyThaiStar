package com.devonfw.mythaistar.booking

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.devonfw.mythaistar.R

/**
 * Created by MGWIZDAL on 2018-03-13.
 */
class BookingConfirmDialog : DialogFragment(), View.OnClickListener {
    lateinit var dateTextView: TextView
    lateinit var nameTextView: TextView
    lateinit var emailTextView: TextView
    lateinit var guestsTextView: TextView
    lateinit var sendButton: Button
    lateinit var cancelButton: Button
    lateinit var argsBundle : Bundle
    var dialogListener: BookingConfirmationDialogListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var rootView = inflater.inflate(R.layout.fragment_dialog_booking_confirm, null)
        dateTextView = rootView.findViewById(R.id.tv_confirm_dialog_date) as TextView
        nameTextView = rootView.findViewById(R.id.tv_confirm_dialog_name) as TextView
        emailTextView = rootView.findViewById(R.id.tv_confirm_dialog_email) as TextView
        guestsTextView = rootView.findViewById(R.id.tv_confirm_dialog_guests) as TextView
        sendButton = rootView.findViewById(R.id.button_confirm_dialog_send) as Button
        cancelButton = rootView.findViewById(R.id.button_confirm_dialog_cancel) as Button
        dialogListener = this.targetFragment as BookingConfirmationDialogListener

        argsBundle = arguments

        dateTextView.text = argsBundle.getString("date")
        nameTextView.text = argsBundle.getString("name")
        emailTextView.text = argsBundle.getString("email")
        guestsTextView.text = argsBundle.getInt("guests").toString()

        sendButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)

        return rootView
    }

    override fun onClick(v: View?) {
        dialogListener = null
        dialogListener = this.targetFragment as BookingConfirmationDialogListener
        when (v?.id) {
            R.id.button_confirm_dialog_send -> {
                dialogListener!!.onConfirmBooking()
                dismiss()
            }
            R.id.button_confirm_dialog_cancel -> dismiss()
        }
    }

    interface BookingConfirmationDialogListener {
        fun onConfirmBooking()
    }
}