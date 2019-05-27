package com.devonfw.mythaistar.news

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import com.devonfw.mythaistar.R
import android.content.DialogInterface
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast



/**
 * Created by zaptsiau on 23.11.2017.
 */
class DeleteNewsDialogFragment() : DialogFragment(){

    private lateinit var newsAdapter : NewsAdapter



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity,R.style.DeleteNewsDialogTheme)
        alertDialogBuilder.setTitle(R.string.screen_news_delete_news__text)

        alertDialogBuilder.setMessage(R.string.screen_news_sure)
        alertDialogBuilder.setPositiveButton(R.string.screen_news_delete_yes, DialogInterface.OnClickListener {
            _, _ -> this.newsAdapter.deleteNews()})

        alertDialogBuilder.setNegativeButton(R.string.screen_news_delete_no, DialogInterface.OnClickListener {
            dialog, _ -> dialog.dismiss() })


        return alertDialogBuilder.create()

    }
    

    fun setFragment(newsAdapter : NewsAdapter){
        this.newsAdapter = newsAdapter
    }
}