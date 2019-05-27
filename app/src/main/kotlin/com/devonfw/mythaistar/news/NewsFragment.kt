package com.devonfw.mythaistar.news
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.data.DummyData
import com.devonfw.mythaistar.home.HomeFragment
import com.devonfw.mythaistar.main.MainComponent

/**
 * Created by zaptsiau on 22.11.2017.
 */
class NewsFragment : BaseFragment<MainComponent, NewsUI, NewsPresenter>(),
        NewsUI,View.OnClickListener {



    override val layoutId =  R.layout.fragment_news
    override val ui= this

    private lateinit  var newsRecycler:RecyclerView
    private lateinit var addNewsFloatingButton: FloatingActionButton
    private lateinit var collapsingToolbarLayout : CollapsingToolbarLayout
    private lateinit var newsAdapter : NewsAdapter
    private lateinit var newsTitleEt : EditText
    private lateinit var newsDescriptionET : EditText
    private lateinit var addNewsDialog : AddNewsDialogFragment
    private var selectedImageBitmap: Bitmap? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, inState: Bundle?): View {
        var view = inflater.inflate(R.layout.fragment_news,container,false)
        //find recyclerview
        newsRecycler = view.findViewById(R.id.newsRecycler) as RecyclerView
        //find collapsingToolbarLayout
        collapsingToolbarLayout = view.findViewById(R.id.newsCollapsingToolbarLayout) as CollapsingToolbarLayout
        // find floatingActionButton
        addNewsFloatingButton = view.findViewById(R.id.fab) as FloatingActionButton

        // add news and notify adapter
        addNewsFloatingButton.setOnClickListener(this)

        //setScrimColor
        collapsingToolbarLayout.setContentScrimColor(Color.WHITE)
        //set layoutManager and Adapter on Recyclerview
        newsRecycler.layoutManager = LinearLayoutManager(activity.applicationContext)
        // as an argument - empty Arraylist<News>
        newsAdapter = NewsAdapter(fragmentManager,DummyData.news)
        newsRecycler.adapter = newsAdapter
        return view
    }

    override fun inject(component: MainComponent) {
        component.inject(this)
    }

    override fun handleBackButton(): Boolean {
        navigator.show(HomeFragment())
        return true
    }

    /**
     * onClick for FloatingActionButton
     * show Dialog for add News
     */
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fab -> showAddNewsDialog()
        }

    }

    private fun showAddNewsDialog(){
        addNewsDialog =  AddNewsDialogFragment()
        addNewsDialog.setStyle(R.style.AddNewsCustomDialogTheme,0)
        addNewsDialog.setFragment(this)
        addNewsDialog.show(fragmentManager,"tag")
    }


    fun checkNews(newsTitle: EditText, newsDescription: EditText) {
        this.newsTitleEt = newsTitle
        this.newsDescriptionET = newsDescription
        val titelText = newsTitleEt.text.toString()
        val descriptionText = newsDescriptionET.text.toString()
        presenter.checkNews(titelText,descriptionText)
    }




    override fun onNewsTitleError() {
        newsTitleEt.error = "this field can't be blank"
    }


    override fun onNewsDescriptionError() {
        newsDescriptionET.error = "this field can't be blank"
    }

    /**
     * add news in newslist and update recyclerview of news
     * with notifyDataSetChanged()...
     * after that close dialog
     */
    override fun onNewsAddSucces() {
        val newsTitel = newsTitleEt.text.toString()
        val newsDescription = newsDescriptionET.text.toString()
        val news = News(newsTitel,newsDescription, imageBitmap = selectedImageBitmap)
        setBitmapOnNUll()
        DummyData.news.add(0,news)
        newsAdapter.notifyDataSetChanged()
        addNewsDialog.dismiss()

    }

    private fun setBitmapOnNUll() {
        this.selectedImageBitmap = null
    }

     fun setSelectedImageBitmap(selectedImage: Bitmap?) {
        this.selectedImageBitmap = selectedImage
    }


}