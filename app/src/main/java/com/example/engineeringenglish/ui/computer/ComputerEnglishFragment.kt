package com.example.engineeringenglish.ui.computer

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
import kotlinx.android.synthetic.main.fragment_computer_english.*

class ComputerEnglishFragment : Fragment() {
    private var list: ArrayList<ImageResult> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_computer_english, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.add(ImageResult("https://tehnichka.pro/wp-content/uploads/2019/07/1-42.jpg", computer_crane))
        list.add(ImageResult("https://www.mgpu.ru/wp-content/uploads/2020/01/1.png", computer_crane_en))
        list.add(ImageResult("https://habrastorage.org/webt/5-/1t/p8/5-1tp8ipv4e4u6av2pqkhr5u_0a.jpeg", computer_en))
        list.add(ImageResult("https://observer.com.ua/wp-content/uploads/2018/04/75970b-650x434.jpg", computer_eng))

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