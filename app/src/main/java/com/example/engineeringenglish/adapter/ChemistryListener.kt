package com.example.engineeringenglish.adapter

import android.widget.TextView
import com.example.engineeringenglish.service.model.ChemistryModel
import java.util.*

interface ChemistryListener {
    fun getAnswers(): HashMap<Int, Int> = hashMapOf()

    fun onClickAnswer(position: Int, answer: ChemistryModel?, id: TextView)
}