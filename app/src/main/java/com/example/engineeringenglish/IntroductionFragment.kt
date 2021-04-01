package com.example.engineeringenglish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_introduction.*


class IntroductionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnim()
        initClick()
    }

    private fun initClick() {
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_main_fragment)
        }
    }

    private fun initAnim() {
        val animation_floating: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.intreation_anim_floating)
        layout_im.startAnimation(animation_floating)

        val animation_phone: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.intreation_anim_phone)
        introduction_phone.startAnimation(animation_phone)

        val animation_one: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.intreation_anim_one)
        introduction_image_one.startAnimation(animation_one)

        val animation_two: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.intreation_anim_two)
        introduction_image_two.startAnimation(animation_two)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
}