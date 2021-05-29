package com.example.engineeringenglish.ui.elements

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
import kotlinx.android.synthetic.main.fragment_chemical_elements.*

class ChemicalElementsFragment : Fragment() {
    private var list: ArrayList<ImageResult> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chemical_elements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.add(ImageResult("https://www.m24.ru/b/d/nBkSUhL2h1ggkciuLPTIoNHumIf08tC41STclaHP72fdcXqTBjTCwGhn99RP7wzT-Nmd-XPcZpwmE-Gm2m92LSGK9G1U9A=1wHL1gMnutvyslGDad1NcQ.jpg", elements_crane))
        list.add(ImageResult("https://i.pinimg.com/originals/29/0f/cf/290fcf9f87f628e60bcb9688cd0b424d.jpg", elements_crane_en))
        list.add(ImageResult("https://zorca.ru/images/%D0%B6%D0%B5%D0%BE%D0%B4%D0%B0%20%D1%81%20%D0%BE%D0%B3%D0%BD%D0%B5%D0%BD%D0%BD%D1%8B%D0%BC%20%D0%BE%D0%BF%D0%B0%D0%BB%D0%BE%D0%BC.jpg?crc=514525606", elements_en))
        list.add(ImageResult("https://elementy.ru/images/kartinka_dnya/picture_of_the_day_azurite_and_malachite_1.jpg", elements_el))
        list.add(ImageResult("https://kamneteka.com/wp-content/uploads/2018/11/smit2.jpg", elements_elm))

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