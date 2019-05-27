package com.devonfw.mythaistar.news
import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devonfw.mythaistar.R
import android.app.Activity.RESULT_OK
import kotlinx.android.synthetic.main.dialog_fragment_add_news.*
import android.widget.*
import android.graphics.BitmapFactory
import android.view.Gravity
import com.devonfw.mythaistar.R.id.title


/**
 * Created by zaptsiau on 23.11.2017.
 */
class AddNewsDialogFragment () : DialogFragment(),View.OnClickListener {


    private lateinit var newsFragment : NewsFragment
    private lateinit var newsTitleET : EditText
    private lateinit var newsDescriptionET : EditText
    private lateinit var newsAddButton : Button
    private lateinit var newsCancelButton : Button
    private lateinit var newsImageButton : ImageButton
    private lateinit var newsLoadTextView : TextView
    private lateinit var newsDefaultPictureTextView : TextView
    private val RESULT_LOAD_IMAGE = 1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view =  inflater!!.inflate(R.layout.dialog_fragment_add_news,container,false)

        newsTitleET = view.findViewById(R.id.dialogFr_add_news_titleEt) as EditText
        newsDescriptionET = view.findViewById(R.id.dialogFr_add_news_descriptionEt) as EditText
        newsAddButton = view.findViewById(R.id.newsAddButton) as Button
        newsCancelButton = view.findViewById(R.id.newsCancelButton) as Button
        newsImageButton = view.findViewById(R.id.imageButton) as ImageButton
        newsLoadTextView = view.findViewById(R.id.newsLoadPictureTV) as TextView
        newsDefaultPictureTextView = view.findViewById(R.id.newsDefaultPictureTV) as TextView
        //pick an image
        newsImageButton.setOnClickListener({
           val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, RESULT_LOAD_IMAGE);
        })
        newsAddButton.setOnClickListener(this)
        newsCancelButton.setOnClickListener(this)
        //dialog.setTitle("Add News")
        dialog.setCanceledOnTouchOutside(false)
        return view
    }

    /**
     * load an image when it exists
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            try {
                val imageUri = data.data
                val imageStream = this.newsFragment.activity.contentResolver.openInputStream(imageUri)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                newsFragment.setSelectedImageBitmap(selectedImage)
                imageButton.setImageBitmap(selectedImage)
                setInfoTextViewsOnNull()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    private fun setInfoTextViewsOnNull() {
        newsDefaultPictureTV.text =""
        newsLoadTextView.text = ""
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.newsAddButton -> newsFragment.checkNews(newsTitleET,newsDescriptionET)
            R.id.newsCancelButton -> dismiss()




        }
    }





    /**
     * set newsFragment
     */
    fun setFragment(newsFragment : NewsFragment){
        this.newsFragment = newsFragment
    }



}



