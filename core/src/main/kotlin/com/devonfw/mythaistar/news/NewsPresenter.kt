package com.devonfw.mythaistar.news

import com.devonfw.mythaistar.base.BasePresenter
import com.sun.org.apache.xpath.internal.operations.Bool
import javax.inject.Inject

/**
 * Created by zaptsiau on 22.11.2017.
 */
class NewsPresenter @Inject constructor() : BasePresenter<NewsUI>()  {


    fun checkNews(newsTitleText: String, newsDescriptionText: String) {
        if(newsTitleText.equals("")){
            view?.onNewsTitleError()
        }
        else if(newsDescriptionText.equals("")){
            view?.onNewsDescriptionError()
        }
        else{
            view?.onNewsAddSucces()
        }
    }
}