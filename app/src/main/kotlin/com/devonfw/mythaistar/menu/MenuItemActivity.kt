package com.devonfw.mythaistar.menu

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
//import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.data.DummyData
import kotlinx.android.synthetic.main.activity_menu_item.*

/**
 * Dumb activity, can be erased
 */
class MenuItemActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val collapesToolbarLayout = findViewById(R.id.collapsingToolbarLayout) as CollapsingToolbarLayout
        val headerImage = findViewById(R.id.header) as ImageView

        // index of the clicked Item in Menu
        val index = intent.extras.get("itemIndex")


        when(index){
            0 -> {
                collapesToolbarLayout.title = "Salads"
                headerImage.setImageResource(R.drawable.salads)
                setPaletteColor(R.drawable.salads)
            }
            1->{
                collapesToolbarLayout.title = "Fish"
                headerImage.setImageResource(R.drawable.fish)
                setPaletteColor(R.drawable.fish)
            }
            2 ->{
                collapesToolbarLayout.title = "Desserts"
                headerImage.setImageResource(R.drawable.desserts)
                setPaletteColor(R.drawable.desserts)
            }
            3 ->{
                collapesToolbarLayout.title = "Drink"
                headerImage.setImageResource(R.drawable.drinkheader)
                setPaletteColor(R.drawable.drink)
            }
        }

        /**
         * get corresponding data from DummyData and set on recycler
         */

        val menuItemRecycler = findViewById<RecyclerView>(R.id.menuItemRecyclerView)
        menuItemRecycler.layoutManager = LinearLayoutManager(this)
       // val adapter = MenuItemRecyclerCustomAdapter(menuItemimages!!, manuItemsNames!!)
      //  menuItemRecycler.adapter = adapter
    }


    /**
     * get color via Palette
     */
    private fun setPaletteColor(imageResource: Int) {

           val headerImage = BitmapFactory.decodeResource(resources, imageResource)

           /*Palette.from(headerImage).generate(object : Palette.PaletteAsyncListener {
               override fun onGenerated(palette: Palette?) {
                   val vibrantCollor = palette?.getVibrantColor(Color.GRAY)
                   val mutedCollor = palette?.getMutedColor(vibrantCollor!!)
                   collapsingToolbarLayout.setContentScrimColor(mutedCollor!!)
               }

           })*/


    }


    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
              onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
