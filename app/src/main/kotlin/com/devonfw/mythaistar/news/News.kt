package com.devonfw.mythaistar.news
import android.graphics.Bitmap
import android.net.Uri
import com.devonfw.mythaistar.R


/**
 * Created by zaptsiau on 22.11.2017.
 */
class News(var titel : String, var description : String,var imageResource : Int = R.drawable.home_item_invite,
           var imageBitmap: Bitmap? = null) {
}