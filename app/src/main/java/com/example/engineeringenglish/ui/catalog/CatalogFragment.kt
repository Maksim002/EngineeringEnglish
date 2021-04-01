package com.example.engineeringenglish.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.adapter.AutoFitGridLayoutManager
import com.example.engineeringenglish.service.adapter.CatalogAdapter
import com.example.engineeringenglish.service.model.CatalogModel
import kotlinx.android.synthetic.main.fragment_catalog.*
import java.util.*


class CatalogFragment : Fragment(){
    var list : ArrayList<CatalogModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))
        list.add(CatalogModel(0, ""))

        val myAdapter = CatalogAdapter()
        myAdapter.update(list)

        catalog_fragment.adapter = myAdapter

        val layoutManager = AutoFitGridLayoutManager(requireContext(), 500)
        catalog_fragment.setLayoutManager(layoutManager);

    }

    override fun onStart() {
        super.onStart()
        (activity as  AppCompatActivity).supportActionBar?.show()
    }
}