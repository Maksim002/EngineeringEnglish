package com.example.engineeringenglish.ui.testmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.engineeringenglish.R
import com.example.engineeringenglish.adapter.TestAdapter
import com.example.engineeringenglish.service.model.ResponseModel
import com.example.engineeringenglish.service.model.ResponseModelInt
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {
    private lateinit var my_adapter: TestAdapter
    private var finalResponse: ArrayList<ResponseModel> = arrayListOf()
    private var finalResponseInt: ArrayList<ResponseModelInt> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        my_adapter = TestAdapter()
        requireActivity().toolbar.setNavigationIcon(null)
        loan_active_status.setOnClickListener {
            findNavController().navigate(R.id.navigation_main_fragment)
        }

        val correctInt = try {
            requireArguments().getSerializable("question")
        }catch (e:Exception){
            null
        }
        finalResponse = correctInt as ArrayList<ResponseModel>

        val wrongInt = try {
            requireArguments().getSerializable("question_int")
        }catch (e:Exception){
            null
        }
        finalResponseInt = wrongInt as ArrayList<ResponseModelInt>

        result_correct_int.text = finalResponseInt[0].correctInt.toString()
        result_wrong_int.text = finalResponseInt[0].wrongInt.toString()

        my_adapter.update(finalResponse)
        recycler_view_test.adapter = my_adapter
    }
}