package com.example.engineeringenglish

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
import com.example.engineeringenglish.service.model.ImageResult
import com.example.engineeringenglish.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_terminology_course.*

class MiningFragment : Fragment() {
    private var list: ArrayList<ImageResult> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mining, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.add(ImageResult("https://www.akchabar.kg/media/news/023295fe-696b-477c-98ff-944b8869ded4.jpg", terminology_crane))
        list.add(ImageResult("https://mining-media.ru/images/2018/01_2018/004_0.jpg", terminology_crane_en))
        list.add(ImageResult("https://mining-media.ru/images/2018/01_2018/022_1.jpg", terminology_en))

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