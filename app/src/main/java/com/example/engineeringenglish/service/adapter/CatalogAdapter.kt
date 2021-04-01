package com.example.engineeringenglish.service.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.model.CatalogModel
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class CatalogAdapter(item :  ArrayList<CatalogModel> = arrayListOf()): GenericRecyclerAdapter<CatalogModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_catalog)
    }

    override fun bind(item: CatalogModel, holder: ViewHolder) {

    }
}