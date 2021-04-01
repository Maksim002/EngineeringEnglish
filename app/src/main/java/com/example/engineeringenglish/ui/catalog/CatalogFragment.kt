package com.example.engineeringenglish.ui.catalog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.engineeringenglish.R
import com.example.engineeringenglish.service.adapter.AutoFitGridLayoutManager
import com.example.engineeringenglish.service.adapter.CatalogAdapter
import com.example.engineeringenglish.service.model.CatalogModel
import com.example.engineeringenglish.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
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
        requireActivity().toolbar.setNavigationIcon(null)
        initRecycler()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initRecycler() {
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_chemistry), ""))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_justice), ""))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_knowledge), ""))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_engineering), ""))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_frue), ""))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_programming), ""))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_repair), ""))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_six), ""))


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