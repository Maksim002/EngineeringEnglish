package com.example.engineeringenglish.ui.developers

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.model.ImageResult
import com.example.engineeringenglish.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_english_developers.*

class EnglishDevelopersFragment : Fragment() {
    private var list: ArrayList<ImageResult> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_english_developers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.add(ImageResult("https://anews.com/tn/50/upload/post/2019/08/27/115874201/gallery/115874201.jpg", developers_crane))
        list.add(ImageResult("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzjiuK94BfaHz8p4NxfXWl2X0wcJH5_pmaxhkgs3Jg86n4PWKDGSeGxgS5Vbv5xApc3Qg&usqp=CAU", developers_crane_en))
        list.add(ImageResult("https://zavistnik.com/wp-content/uploads/2020/01/TOP-IT-professij-750x375.jpg", developers_en))

        MainActivity.alert.show()
        if (list.size != 0){
            initGlide()
        }
    }

    private fun initGlide() {
        for (i in 1..list.size){
            Glide
                .with(this)
                .load(list[i -1].url)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                        if (i == list.size){
                            MainActivity.alert.hide()
                        }
                        return false
                    }
                })
                .into(list[i -1].imageView);
        }
    }
}