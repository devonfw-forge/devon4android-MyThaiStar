package com.devonfw.mythaistar.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.home.HomeFragment
import com.devonfw.mythaistar.main.MainComponent
import com.google.android.gms.tasks.Tasks.call
import org.w3c.dom.Text

/**
 * Created by zaptsiau on 27.11.2017.
 */
class ContactsFragment : BaseFragment<MainComponent, ContactsUI, ContactsPresenter>(),
        ContactsUI, View.OnClickListener{


    override val layoutId = R.layout.fragment_contacts
    override val ui = this

    lateinit var nummerTV : TextView
    lateinit var emailTV : TextView

    override fun inject(component: MainComponent) {
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, inState: Bundle?): View {
        val view =inflater.inflate(R.layout.fragment_contacts,container,false)
        nummerTV = view.findViewById(R.id.nummer) as TextView
        emailTV = view.findViewById(R.id.mail) as TextView
        nummerTV.setOnClickListener(this)
        emailTV.setOnClickListener(this)
        return view
    }



    override fun handleBackButton(): Boolean {
        navigator.show(HomeFragment())
        return  true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.nummer -> call()
            R.id.mail -> sendEmail()
        }
    }

    private fun sendEmail() {
        val mail = emailTV.text.toString()
        val i = Intent(Intent.ACTION_SEND)
        i.type = "plain/text"
        i.putExtra(Intent.EXTRA_EMAIL,arrayOf(mail))
        try {
            startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(activity.applicationContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun call() {
        val phone = nummerTV.text.toString()
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:"+phone)
        startActivity(callIntent)

    }
}