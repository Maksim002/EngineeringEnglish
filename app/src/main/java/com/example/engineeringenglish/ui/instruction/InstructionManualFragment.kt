package com.example.engineeringenglish.ui.instruction

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.engineeringenglish.R
import com.example.engineeringenglish.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_instruction_manual.*

class InstructionManualFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruction_manual, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as  AppCompatActivity).toolbar.navigationIcon = null;
        initClick()
    }

    private fun initClick() {
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.introduction_fragment)
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as  AppCompatActivity).supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (activity as AppCompatActivity).window.statusBarColor = resources.getColor(R.color.colorIcon);
        }
    }
}