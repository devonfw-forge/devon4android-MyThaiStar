package com.devonfw.mythaistar.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.common.bindView
import kotlin.properties.Delegates

/**
 * Checkbox with a text field next to it. Text can be made clickable
 * in order to redirect to start an Intent with an [Intent.ACTION_VIEW] action.
 */
class LinkedCheckBox @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    val checkBox by bindView<CheckBox>(R.id.check_box)
    private val tvLink by bindView<TextView>(R.id.tv_link)

    var text by Delegates.observable("") { _, _, _ -> setLink(null) }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_linked_check_box, this)
    }

    /**
     * Sets a link to the previously set text and highlights it.
     * By default whole text is made clickable. If a passed [url]
     * is null or blank text is not clickable.
     *
     * @param url An URL to which a click action should redirect.
     * @param offset Offset from start of [text] from which link should be available.
     * @param length Length of a highlighted link counted from [offset].
     */
    fun setLink(url: String?,
                offset: Int = 0,
                length: Int = text.length - offset) {
        if (null == url || url.isBlank()) {
            tvLink.text = text
            tvLink.movementMethod = null
        } else {
            val spannableString = SpannableString(text)
            spannableString.setSpan(LinkSpan(url),
                    offset, length + offset,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvLink.text = spannableString
            tvLink.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}

private class LinkSpan(val url: String) : ClickableSpan() {
    override fun onClick(view: View) {
        view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    override fun updateDrawState(drawState: TextPaint) {
        drawState.color = drawState.linkColor
    }
}
