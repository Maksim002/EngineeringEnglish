package com.example.engineeringenglish.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.model.CatalogModel
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_catalog.view.*

class CatalogAdapter(var listener: CatalogListener ,item :  ArrayList<CatalogModel> = arrayListOf()): GenericRecyclerAdapter<CatalogModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_catalog)
    }

    override fun bind(item: CatalogModel, holder: ViewHolder) {
        holder.itemView.catalog_image.setImageDrawable(item.image)
        holder.itemView.name_catalog.text = item.name
        holder.itemView.catalog_layout.setOnClickListener {
            listener.catalogClockListener(holder.adapterPosition)
        }
    }
}