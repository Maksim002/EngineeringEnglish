      package com.example.engineeringenglish.ui.soils

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
import kotlinx.android.synthetic.main.fragment_mountain_soils.*
import kotlinx.android.synthetic.main.fragment_terminology_course.*
import kotlinx.android.synthetic.main.fragment_terminology_course.terminology_crane
import kotlinx.android.synthetic.main.fragment_terminology_course.terminology_crane_en
import kotlinx.android.synthetic.main.fragment_terminology_course.terminology_en

class MountainSoilsFragment : Fragment() {
    private var list: ArrayList<ImageResult> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mountain_soils, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.add(ImageResult("https://masheka.by/uploads/posts/2020-06/1592410661_0002.jpg", soils_crane))
        list.add(ImageResult("https://fireman.club/wp-content/uploads/2017/08/Grunt.jpg", soils_crane_en))
        list.add(ImageResult("http://www.ocenin.ru/wp-content/uploads/2017/01/654-2.jpg", soils_en))

        if (list.size != 0){
            MainActivity.alert.show()
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
                        if (i <= list.size){
                            MainActivity.alert.hide()
                        }
                        return false
                    }
                })
                .into(list[i -1].imageView);
        }
    }
}