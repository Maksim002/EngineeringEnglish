package com.example.engineeringenglish.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringenglish.R
import com.example.engineeringenglish.adapter.ChemistryListener
import com.example.engineeringenglish.service.model.AppPreferences
import com.example.engineeringenglish.service.model.ChemistryModel
import kotlinx.android.synthetic.main.item_chemistry.view.*

class ViewHolder(itemView: View, private val listener: ChemistryListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(answer: ChemistryModel?, questionPosition: Int) {
        itemView.text_chemistry.setOnClickListener {
            if (AppPreferences.numberCharacters == 0){
                listener.onClickAnswer(questionPosition, answer, itemView.text_chemistry)
                AppPreferences.numberCharacters = 1
            }
        }
    }

    fun isAnswered(isAnswerSelected: Boolean, isAnswered: Boolean, isCorrect: Boolean) {
        if (isAnswerSelected && isCorrect) {
            itemView.text_chemistry.setBackgroundResource(R.drawable.color_grey)
        } else if (isAnswered) {
            itemView.text_chemistry.setBackgroundResource(R.drawable.color_red)
        } else {
            itemView.text_chemistry.setBackgroundResource(R.drawable.circle_orange_background_bottom)
        }
    }
}