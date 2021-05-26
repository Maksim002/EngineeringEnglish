package com.example.engineeringenglish.ui.splash

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.engineeringenglish.R


class SplashOneFragment : Fragment() {
    val handler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnim()
    }

    private fun initAnim() {
        handler.postDelayed(Runnable { // Do something after 5s = 500ms
            findNavController().navigate(R.id.navigation_manual_fragment) }, 1000)
    }

    override fun onStart() {
        super.onStart()
        (activity as  AppCompatActivity).getSupportActionBar()!!.setDisplayHomeAsUpEnabled(false);
        (activity as  AppCompatActivity).supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            (activity as AppCompatActivity).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            (activity as AppCompatActivity).getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }
}