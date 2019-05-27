package com.devonfw.mythaistar.data

import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.news.News



/**
 * Created by zaptsiau on 16.11.2017.
 */
class DummyData {
    companion object {
       var menuDrawableList = arrayListOf<Int>(R.drawable.salads,
               R.drawable.fish, R.drawable.dess, R.drawable.drink)
       var menuNameList = arrayListOf<String>("Salads", "Fish","Desserts", "Drink")

        var menuItemMap = hashMapOf<Int, Pair<ArrayList<Int>, ArrayList<String>>>(
                 0 to Pair(arrayListOf
                 (R.drawable.side_salad, R.drawable.sophies_salad, R.drawable.king_garden_salad, R.drawable.cashew_teriyaki_salad,R.drawable.garde_salad,R.drawable.burger_king_salat),  arrayListOf("Side Salad","Sophie's Salad","King Garden Salad","Cashew Teriyak Salad","Garden Salad","Burger King Salad")),
                       1 to Pair(arrayListOf(R.drawable.fish_lotus_seafood,
                               R.drawable.fish_yoko_sushi,
                               R.drawable.fish_tekka_maki_sushi,
                               R.drawable.fish_oishi_sushi,
                               R.drawable.fish_yuimini_sushi,
                               R.drawable.sushi), arrayListOf("Lotus Seafood", "Tekka Maki Sushi","Yoko Sushi", "Oishi Sushi", "Yuimini Sushi","Sushi")),
                       2 to Pair(arrayListOf(R.drawable.strawberry_cake,
                               R.drawable.chocolate_cake,
                               R.drawable.ice_cream,
                               R.drawable.apple_cake,
                               R.drawable.chocolate_cake_2,
                               R.drawable.respberry_cake), arrayListOf("Stawberry Cake","Chocolate Cake", "Ice Cream", "Apple Cake", "Chocolate Cake", "Respberry Cake")),
                       3 to Pair(arrayListOf(R.drawable.water,
                               R.drawable.beer,
                               R.drawable.cola,
                               R.drawable.vodka,
                               R.drawable.cocktail,
                               R.drawable.mango), arrayListOf("Water", "Beer","Cola", "Vodka", "Cocktail", "Mango"))
        )


        var galleryImagesTopArray = arrayListOf<Int>(R.drawable.res1,R.drawable.res2
        ,R.drawable.res3,R.drawable.res4,R.drawable.res5,R.drawable.res6)

        var galleryImagesBottomArray = arrayListOf<Int>(R.drawable.res7,R.drawable.res8,
                R.drawable.res9,R.drawable.res10, R.drawable.res11,R.drawable.res12)


        var news = arrayListOf<News>()
    }

    
}
