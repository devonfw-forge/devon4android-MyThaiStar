package com.devonfw.mythaistar.gallery

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import com.devonfw.mythaistar.R

/**
 * Created by zaptsiau on 21.11.2017.
 */
class GalleryAdapter(var imageArray : ArrayList<Int>, var bigImage : ImageView) :
        RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bindItems(position, imageArray)

        holder.itemView?.setOnClickListener({
            bigImage.setImageResource(imageArray[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return imageArray.size
    }



    class GalleryViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(position: Int, imageArray: ArrayList<Int>) {
            var smallImage = itemView.findViewById(R.id.gallerySmallImage) as ImageView
            smallImage.setImageResource(imageArray[position])

        }




    }
}