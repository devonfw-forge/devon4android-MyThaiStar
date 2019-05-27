package com.devonfw.mythaistar.booking

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.common.bindView
import com.devonfw.mythaistar.view.LinkedCheckBox

class BookTableView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

  private val etDateAndTime by bindView<EditText>(R.id.et_date_and_time)
  private val etName by bindView<EditText>(R.id.et_name)
  private val etEmail by bindView<EditText>(R.id.et_email)
  private val npGuests : NumberPicker by bindView(R.id.np_guests)
  private val checkBoxAcceptTerms by bindView<LinkedCheckBox>(R.id.check_box_accept_terms)
  private val btnBookTable by bindView<Button>(R.id.btn_book_table)

  private var guestsNumber : Int = 0

    /**
   * Getters for members
   */
  val getEtDateAndTime get() = etDateAndTime
  val getEtName get() = etName
  val getEtEmail get() = etEmail
  val getNpGuests get() = guestsNumber
  val getCheckBoxAcceptTerms get() =  checkBoxAcceptTerms
  val getBtnBookTable get() = btnBookTable


  init {
    LayoutInflater.from(context).inflate(R.layout.view_book_table, this)
    prepareNP()
    val linkText = context.resources.getString(R.string.view_book_table_terms)
    val checkBoxText = context.resources.getString(R.string.view_book_table_accept_terms_label, linkText)
    checkBoxAcceptTerms.text = checkBoxText
    checkBoxAcceptTerms.setLink(BookingFragment.URL_TERMS, checkBoxText.length - linkText.length)
  }

  private fun prepareNP() {
    npGuests.minValue = 0
    npGuests.maxValue = 10
    npGuests.wrapSelectorWheel = true
    npGuests.setOnValueChangedListener({ _, _, newVal ->
      guestsNumber = newVal
    })
  }
}
