package com.devonfw.mythaistar.news
import android.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.data.DummyData


/**
 * Created by zaptsiau on 22.11.2017.
 */
class NewsAdapter(var fragmentManager:FragmentManager, var news : ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(),
        OnRecyclerItemClickListener {

    private var position : Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_news_recycler_item,parent,false)
        var newsViewHolder = NewsViewHolder(view)
        newsViewHolder.setRecyclerItemToggleClickListener(this)
        return newsViewHolder

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindItems(news, position)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    /**
     * save the clicked position and
     * show deleteNewsDialog
     */
    override fun onToggleClick(adapterPosition: Int) {
        this.position = adapterPosition
        showDeleteNewsDialog()

    }

    /**
     * remove News at given position
     * and update data
     */
    fun deleteNews(){
        DummyData.news.removeAt(position)
        notifyDataSetChanged()
    }

    fun showDeleteNewsDialog(){
        val deleteDialog = DeleteNewsDialogFragment()
        deleteDialog.setFragment(this)
        deleteDialog.show(fragmentManager,"tag")
    }




    //ViewHolder Class
    class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        private lateinit var listener: OnRecyclerItemClickListener

        fun bindItems(news: ArrayList<News>, position: Int) {
            val newsImage = itemView.findViewById(R.id.newsImage) as ImageView
            val titel = itemView.findViewById(R.id.newsTitel) as TextView
            val description = itemView.findViewById(R.id.newsDescription) as TextView
            val removeButton = itemView.findViewById(R.id.newsRemoveButton) as Button
            removeButton.setOnClickListener(this)

            if(news[position].imageBitmap != null){
                newsImage.setImageBitmap(news[position].imageBitmap)
            }
            else{
                newsImage.setImageResource(news[position].imageResource)
            }

            titel.text = news[position].titel
            description.text = news[position].description
        }

        fun setRecyclerItemToggleClickListener(listener: OnRecyclerItemClickListener) {
            this.listener = listener
        }

    override fun onClick(v: View?) {
        val position = adapterPosition
        listener.onToggleClick(position)
    }


    }


}