package com.devonfw.mythaistar.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.devonfw.mythaistar.R

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_logged)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tokenTV = findViewById<TextView>(R.id.token_tv)
        val token = intent.getStringExtra(EXTRA_TOKEN)
        tokenTV.text = token
    }
}