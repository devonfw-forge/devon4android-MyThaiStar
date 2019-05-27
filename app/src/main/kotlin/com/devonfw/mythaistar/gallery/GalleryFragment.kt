package com.devonfw.mythaistar.gallery

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.data.DummyData
import com.devonfw.mythaistar.home.HomeFragment
import com.devonfw.mythaistar.main.MainComponent



/**
 * Created by zaptsiau on 21.11.2017.
 */
class GalleryFragment : BaseFragment<MainComponent, GalleryUI, GalleryPresenter>(), GalleryUI {

    override val layoutId = R.layout.fragment_gallery
    override val ui = this



    override fun inject(component: MainComponent) {
        component.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, inState: Bundle?): View {
        var view = inflater.inflate(R.layout.fragment_gallery,container,false)

        var bigImmage = view.findViewById(R.id.galleryBigImage) as ImageView
        //setze das erte Bild am Anfang
        bigImmage.setImageResource(DummyData.galleryImagesTopArray[0])

        //initialisiere recyclerview oben und unten
        var galleryRecyclerTop = view.findViewById(R.id.galleryRecyclerTop) as RecyclerView
        var galleryRecyclerBottom = view.findViewById(R.id.galleryRecyclerBottom) as RecyclerView
        galleryRecyclerTop.adapter = GalleryAdapter(DummyData.galleryImagesTopArray,bigImmage)
        galleryRecyclerTop.layoutManager = LinearLayoutManager(activity.applicationContext,LinearLayoutManager.HORIZONTAL,false)


        galleryRecyclerBottom.adapter = GalleryAdapter(DummyData.galleryImagesBottomArray,bigImmage)
        galleryRecyclerBottom.layoutManager = LinearLayoutManager(activity.applicationContext,LinearLayoutManager.HORIZONTAL,false)
        return view
    }


    override fun handleBackButton(): Boolean {
        navigator.show(HomeFragment())
        return true
    }

}