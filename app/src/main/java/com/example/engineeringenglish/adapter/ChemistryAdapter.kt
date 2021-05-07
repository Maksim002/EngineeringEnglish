package com.example.engineeringenglish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.model.ChemistryModel


class ChemistryAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var list: List<ChemistryModel> = arrayListOf()
    private lateinit var listener: ChemistryListener
    private var questionPosition = 0

    fun update(list: List<ChemistryModel>?, listener: ChemistryListener?, position: Int) {
        this.list = list!!
        this.listener = listener!!
        questionPosition = position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_chemistry, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], questionPosition)
        val map = listener.getAnswers()
        val isAnswerSelected = map.containsKey(questionPosition)
        val isAnswered = map[questionPosition] != null && map[questionPosition] == list[position].id
        val isCorrect = list[position].correct
        holder.isAnswered(isAnswerSelected, isAnswered, isCorrect)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}