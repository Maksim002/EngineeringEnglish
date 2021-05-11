package com.example.engineeringenglish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.engineeringenglish.adapter.ChemistryAdapter
import com.example.engineeringenglish.adapter.ChemistryListener
import com.example.engineeringenglish.service.model.AppPreferences
import com.example.engineeringenglish.service.model.ChildModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_chemistry.*

class ChemistryFragment : Fragment(), ChemistryListener {
    private lateinit var myAdapter: ChemistryAdapter
    private var item: ArrayList<ChildModel> = arrayListOf()
    private var list: HashMap<String, ChildModel> = hashMapOf()
    private var mapDate: HashMap<String, HashMap<String, ChildModel>> = hashMapOf()
    private val map = HashMap<Int, Int>()
    private var itemPosition: ArrayList<String> = arrayListOf()
    private lateinit var dataBase: DatabaseReference
    private var questionName = "term_"

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
        firebaseDate()
    }

    private fun firebaseDate() {
        myAdapter = ChemistryAdapter()

        //Подключаемся к базе firebase
        for (i in 1..16) {
            val questionValue = questionName + i
            itemPosition.add(questionValue)
            dataBase = FirebaseDatabase.getInstance().getReference(questionValue)
            dataBase.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //Если база не является пустой
                    if (snapshot.exists()) {
                        val key = snapshot.key
                        val value = snapshot.children
                        value.forEach {
                            try {
                                val l = it.getValue(ChildModel::class.java)
                                list.put(it.key.toString(), l!!)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        mapDate.put(key.toString(), list)
                        if (itemPosition[0] == key) {
                            if (list.getValue("question_id").question != null) {
                                text_chemistry.text = list.getValue("question_id").question
                                transcription_chemistry.text = list.getValue("question_id").transcription
                            }
                            list.forEach {
                                if (it.key != "question_id") {
                                    item.add(it.value)
                                }
                                myAdapter.update(item, this@ChemistryFragment, item.size)
                                chemistry_recycler.adapter = myAdapter
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun getAnswers(): HashMap<Int, Int> {
        return map
    }

    override fun onClickAnswer(position: Int, answer: ChildModel?, id: TextView) {

        map[position] = answer!!.id!!
        myAdapter.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        AppPreferences.numberCharacters = 0
    }
}