package com.example.engineeringenglish.ui.catalog

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.engineeringenglish.R
import com.example.engineeringenglish.adapter.AutoFitGridLayoutManager
import com.example.engineeringenglish.adapter.CatalogAdapter
import com.example.engineeringenglish.adapter.CatalogListener
import com.example.engineeringenglish.service.model.CatalogModel
import com.rahman.dialog.Utilities.SmartDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import java.util.*


class CatalogFragment : Fragment(), CatalogListener{
    private var list : ArrayList<CatalogModel> = arrayListOf()
    private lateinit var myAdapter: CatalogAdapter
    private var doubleСlick = false
    private val handler = Handler()
    private  var clearPosition = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        if (list.size != 0)   {
            list.clear()
        }
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_chemistry), "Элементы"))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_justice), "Измерения"))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_knowledge), "Материалы"))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_engineering), "Терминология"))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_frue), "Горное дело"))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_programming), "IT разработка"))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_repair), "Компьютер"))
        list.add(CatalogModel(resources.getDrawable(R.drawable.ic_six), "Почва"))

        myAdapter = CatalogAdapter(this)
        myAdapter.update(list)
        catalog_fragment.adapter = myAdapter

        val layoutManager = AutoFitGridLayoutManager(requireContext(), 500)
        catalog_fragment.setLayoutManager(layoutManager);
    }

    override fun catalogClockListener(position: Int) {
        if (clearPosition != position){
            clearPosition = position
            alertDialog(position)
        }
        handler.postDelayed(Runnable { // Do something after 5s = 500ms
            clearPosition = -1
        }, 1500)
    }

    private fun alertDialog(position: Int){
        SmartDialogBuilder(context)
            .setTitle("Привет")
            .setSubTitle("Мы подобрали для Вас термины, которые наиболее часто встречаются в работе инженера.")
            .setCancalable(true)
            .setTitleFont(Typeface.SERIF) //set title font
            .setPositiveButton("Краткий курс") { smartDialog ->
                curseDialog(position)
                smartDialog.dismiss()
            }
            .setNegativeButton("Тестирование"){ tttDialog ->
                curseDetail(position)
                tttDialog.dismiss()
            }.build().show()
    }

    private fun curseDialog(position: Int){
        if (position == 3){
            findNavController().navigate(R.id.terminologyCourseFragment)
        }else if (position ==6){
            findNavController().navigate(R.id.computerEnglishFragment)
        }else if (position == 1){
            findNavController().navigate(R.id.measurementFragment)
        }else if (position == 5){
            findNavController().navigate(R.id.englishDevelopersFragment)
        }else if (position == 2){
            findNavController().navigate(R.id.materialsFragment)
        }else if (position == 4){
            findNavController().navigate(R.id.miningFragment)
        }else if (position == 0){
            findNavController().navigate(R.id.chemicalElementsFragment)
        }else if (position == 7){
            findNavController().navigate(R.id.mountainSoilsFragment)
        }
    }

    private fun curseDetail(position: Int){
        val bundle = Bundle()
        if (!doubleСlick) {
            if (position == 3) {
                bundle.putString("test", "term_")
                bundle.putString("test_title", "Терминология - Terminology")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            } else if (position == 6) {
                bundle.putString("test", "PC_")
                bundle.putString("test_title", "Компьютер - Computer")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            } else if (position == 1) {
                bundle.putString("test", "meas_")
                bundle.putString("test_title", "Измерения - Measurements")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            } else if (position == 5) {
                bundle.putString("test", "it_")
                bundle.putString("test_title", "Разработка - Development")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            } else if (position == 2) {
                bundle.putString("test", "mat_")
                bundle.putString("test_title", "Материалы - Materials")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            } else if (position == 4) {
                bundle.putString("test", "mi_")
                bundle.putString("test_title", "Горное дело - Mining industry")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            } else if (position == 0) {
                bundle.putString("test", "ch_")
                bundle.putString("test_title", "Элементы - Elements")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            } else if (position == 7) {
                bundle.putString("test", "til_")
                bundle.putString("test_title", "Почва - Soil")
                findNavController().navigate(R.id.navigation_chemistry_fragment, bundle)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as  AppCompatActivity).supportActionBar?.show()
    }
}