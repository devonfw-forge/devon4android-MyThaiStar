package com.devonfw.mythaistar.booking.data

/**
 * Created by MGWIZDAL on 2018-03-13.
 */
data class BookForm(var dateAndTime: String = "",
                    var name: String = "",
                    var email:String = "",
                    var guests: Int = 0,
                    var isChecked: Boolean = false) {}