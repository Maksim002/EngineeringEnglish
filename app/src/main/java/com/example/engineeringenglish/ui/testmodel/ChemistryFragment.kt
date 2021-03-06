package com.example.engineeringenglish.ui.testmodel

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.engineeringenglish.R
import com.example.engineeringenglish.adapter.ChemistryAdapter
import com.example.engineeringenglish.adapter.ChemistryListener
import com.example.engineeringenglish.adapter.ViewHolder
import com.example.engineeringenglish.service.model.AppPreferences
import com.example.engineeringenglish.service.model.ChildModel
import com.example.engineeringenglish.service.model.ResponseModel
import com.example.engineeringenglish.service.model.ResponseModelInt
import com.example.engineeringenglish.ui.main.MainActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_chemistry.*
import kotlinx.android.synthetic.main.fragment_instruction_manual.*
import kotlinx.android.synthetic.main.item_chemistry.view.*


class ChemistryFragment : Fragment(), ChemistryListener {
    private lateinit var myAdapter: ChemistryAdapter
    private var item: ArrayList<ChildModel> = arrayListOf()
    private var list: HashMap<String, ChildModel> = hashMapOf()
    private var mapDate: HashMap<String, HashMap<String, ChildModel>> = hashMapOf()
    private val map = HashMap<Int, Int>()
    private var itemPosition: ArrayList<String> = arrayListOf()
    private lateinit var dataBase: DatabaseReference
    private var questionName = ""
    private var dataKey = ""
    private var counter = 1
    private val handler = Handler()
    private var finalResponse: ArrayList<ResponseModel> = arrayListOf()
    private var finalResponseInt: ArrayList<ResponseModelInt> = arrayListOf()
    private var correctInt = 0
    private var wrongInt = 0
    private var firstEnters = false

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

        questionName = try {
            requireArguments().getString("test").toString()
        } catch (e: Exception) {
            ""
        }

        val question = try {
            requireArguments().getString("test_title").toString()
        } catch (e: Exception) {
            ""
        }

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = question

        firebaseDate()
    }

    private fun firebaseDate(id: TextView? = null) {
        myAdapter = ChemistryAdapter()
        //???????????????????????? ?? ???????? firebase
        if (counter != 17) {
            if (firstEnters == false) {
                MainActivity.alert.show()
            }
            if (questionName != "") {
                val questionValue = questionName + counter
                itemPosition.add(questionValue)
                dataBase = FirebaseDatabase.getInstance().getReference(questionValue)
                dataBase.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        //???????? ???????? ???? ???????????????? ????????????
                        if (snapshot.exists()) {
                            dataKey = snapshot.key.toString()
                            val value = snapshot.children
                            value.forEach {
                                try {
                                    val l = it.getValue(ChildModel::class.java)
                                    list.put(it.key.toString(), l!!)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                            mapDate.put(dataKey, list)
                            try {
                                if (itemPosition[0] == dataKey) {
                                    if (list.getValue("question_id").question != null) {
                                        text_chemistry.text = list.getValue("question_id").question
                                        transcription_chemistry.text =
                                            list.getValue("question_id").transcription
                                    }
                                    list.forEach {
                                        if (it.key != "question_id") {
                                            item.add(it.value)
                                        }
                                        myAdapter.update(item, this@ChemistryFragment, item.size)
                                        chemistry_recycler.adapter = myAdapter
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        try {
                            id!!.isEnabled = true
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        try {
                            layout_chemistry.visibility = View.VISIBLE
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        MainActivity.alert.hide()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.navigation_main_fragment)
                        MainActivity.alert.hide()
                    }
                })
            }
        } else {
            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            val bundle = Bundle()
            bundle.putSerializable("question_int", finalResponseInt)
            bundle.putSerializable("question", finalResponse)
            findNavController().navigate(R.id.navigation_result_fragment, bundle)
        }
    }

    override fun getAnswers(): HashMap<Int, Int> {
        return map
    }

    override fun onClickAnswer(
        position: Int,
        answer: ChildModel?,
        id: TextView,
        holder: ViewHolder
    ) {
        try {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            firstEnters = true
            id.isEnabled = true
            if (answer!!.correct == true) {
                correctInt++
            } else {
                wrongInt++
            }
            if (counter == 16) {
                finalResponseInt.add(ResponseModelInt(correctInt, wrongInt))
            }

            finalResponse.add(ResponseModel(answer.translation.toString(), answer.correct!!))

            AppPreferences.isLogined = false
            map[position] = answer.id!!
            myAdapter.notifyDataSetChanged()
            handler.postDelayed(Runnable { // Do something after 5s = 500ms
                myAdapter.question??olors(position, holder)
                item.clear()
                itemPosition.clear()
                AppPreferences.isLogined = true
                AppPreferences.numberCharacters = 0
                counter++
                firebaseDate(id)
            }, 200)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        AppPreferences.numberCharacters = 0
    }
}