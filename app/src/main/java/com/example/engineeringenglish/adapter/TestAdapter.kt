package com.example.engineeringenglish.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.model.ResponseModel
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_test.view.*

class TestAdapter(item: ArrayList<ResponseModel> = arrayListOf()): GenericRecyclerAdapter<ResponseModel>(item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_test)
    }

    override fun bind(item: ResponseModel, holder: ViewHolder) {
        holder.itemView.test_text.text = item.name
         isAnswered(item.boolean, holder)
    }

    fun isAnswered(isCorrect: Boolean, holder: ViewHolder) {
        if (isCorrect) {
            holder.itemView.test_text.setBackgroundResource(R.drawable.color_grey)
        } else {
            holder.itemView.test_text.setBackgroundResource(R.drawable.color_red)
        }
    }
}