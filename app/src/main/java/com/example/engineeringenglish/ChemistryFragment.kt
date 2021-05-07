package com.example.engineeringenglish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.engineeringenglish.adapter.ChemistryAdapter
import com.example.engineeringenglish.adapter.ChemistryListener
import com.example.engineeringenglish.service.model.AppPreferences
import com.example.engineeringenglish.service.model.ChemistryModel
import kotlinx.android.synthetic.main.fragment_chemistry.*
import java.util.*


class ChemistryFragment : Fragment(), ChemistryListener {
    private lateinit var myAdapter: ChemistryAdapter
    private val list: ArrayList<ChemistryModel> = arrayListOf()
    private val map = HashMap<Int, Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chemistry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter = ChemistryAdapter()

        list.add(ChemistryModel(1, "wdwd", 0, false))
        list.add(ChemistryModel(2, "wdwd", 0, false))
        list.add(ChemistryModel(3, "wdwd", 0, true))
        list.add(ChemistryModel(4, "wdwd", 0, false))

        myAdapter.update(list, this, list.size)
        chemistry_recycler.adapter = myAdapter
    }

    override fun getAnswers(): HashMap<Int, Int> {
        return map
    }

    override fun onClickAnswer(position: Int, answer: ChemistryModel?, id: TextView) {
        map[position] = answer!!.id
        myAdapter.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        AppPreferences.numberCharacters = 0
    }
}