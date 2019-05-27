package com.devonfw.mythaistar.booking

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.common.bindView
import com.devonfw.mythaistar.view.LinkedCheckBox

class InviteFriendsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {


  private val etDateAndTime by bindView<EditText>(R.id.et_date_and_time)
  private val etName by bindView<EditText>(R.id.et_name)
  private val etEmail by bindView<EditText>(R.id.et_email)
  private val etFriends by bindView<EditText>(R.id.et_friends)
  private val checkBoxAcceptTerms by bindView<LinkedCheckBox>(R.id.check_box_accept_terms)
  private val btnInviteFriends by bindView<Button>(R.id.btn_invite_friends)

  /**
   * Getters for members
   */
  val getEtDateAndTime get() =  etDateAndTime
  val getEtName get() = etName
  val getEtEmail get() = etEmail
  val getEtFriends get() =  etFriends
  val getCheckBoxAcceptTerms get() = checkBoxAcceptTerms
  val getBtnInviteFriends get() = btnInviteFriends




  init {
    LayoutInflater.from(context).inflate(R.layout.view_invite_friends, this)
    val linkText = context.resources.getString(R.string.view_invite_friends_terms)
    val checkBoxText = context.resources.getString(R.string.view_invite_friends_accept_terms_label, linkText)
    checkBoxAcceptTerms.text = checkBoxText
    checkBoxAcceptTerms.setLink(BookingFragment.URL_TERMS, checkBoxText.length - linkText.length)
  }
}
