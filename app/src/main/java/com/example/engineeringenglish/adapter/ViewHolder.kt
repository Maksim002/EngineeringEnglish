package com.example.engineeringenglish.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.model.AppPreferences
import com.example.engineeringenglish.service.model.ChildModel
import kotlinx.android.synthetic.main.item_chemistry.view.*

class ViewHolder(itemView: View, private val listener: ChemistryListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(answer: ChildModel?, questionPosition: Int) {
        itemView.text_chemistry.text = answer!!.translation

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