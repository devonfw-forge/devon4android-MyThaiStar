package com.devonfw.mythaistar.home

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.common.bindView

class HomeItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : CardView(context, attrs) {

  private val ivImage: ImageView by bindView(R.id.iv_image)
  private val tvTitle: TextView by bindView(R.id.tv_title)
  private val tvDescription: TextView by bindView(R.id.tv_description)
  private val btnAction: Button by bindView(R.id.btn_action)

  init {
    LayoutInflater.from(context).inflate(R.layout.view_home_item, this)
  }

  fun setContent(@DrawableRes image: Int,
                 @StringRes title: Int,
                 @StringRes description: Int,
                 @StringRes buttonLabel: Int,
                 buttonAction: (View) -> Unit) {
    ivImage.setImageDrawable(ContextCompat.getDrawable(context, image))
    tvTitle.setText(title)
    tvDescription.setText(description)
    btnAction.setText(buttonLabel)
    btnAction.setOnClickListener(buttonAction)
  }
}
