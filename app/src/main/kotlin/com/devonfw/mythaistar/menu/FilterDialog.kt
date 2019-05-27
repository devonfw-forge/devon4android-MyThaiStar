package com.devonfw.mythaistar.menu

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.devonfw.mythaistar.R
import android.view.inputmethod.EditorInfo
import org.w3c.dom.Text


/**
 * Created by MGWIZDAL on 2018-03-08.
 */
class FilterDialog : DialogFragment(), View.OnClickListener {
    var maxPrice = 0
    lateinit var maxPriceSeekBar: SeekBar
    lateinit var acceptButton: Button
    lateinit var maxPriceSeekBarTextView: TextView
    lateinit var dialogTextView: TextView
    var maxPriceDialogListener: MaxPriceDialogListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var rootView = inflater.inflate(R.layout.fragment_dialog_filtering, null)
        maxPriceSeekBar = rootView.findViewById(R.id.seek_bar) as SeekBar
        maxPriceSeekBarTextView = rootView.findViewById(R.id.seek_bar_price_tv) as TextView
        acceptButton = rootView.findViewById(R.id.accept_button) as Button
        dialogTextView = rootView.findViewById(R.id.describe_filter_max_price) as TextView
        maxPriceSeekBar.max = 50
        dialogTextView.text = getString(R.string.set_maxmimum_price)
        maxPriceDialogListener = this.targetFragment as MaxPriceDialogListener

        maxPriceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                maxPrice = seekBar.progress
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                maxPriceSeekBarTextView.text = seekBar.progress.toString()
            }

        })
        acceptButton.setOnClickListener(this)
        return rootView
    }

    override fun onClick(v: View?) {
        maxPriceDialogListener = null
        maxPriceDialogListener = this.targetFragment as MaxPriceDialogListener
        if (maxPriceDialogListener != null) {
            if (v == acceptButton) {
                maxPriceDialogListener!!.onFinishDialog(maxPrice)
                dismiss()
            }
        }
    }

    interface MaxPriceDialogListener {
        fun onFinishDialog(maxPrice: Int)
    }
}
