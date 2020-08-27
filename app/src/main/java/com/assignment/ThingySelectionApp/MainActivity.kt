package com.assignment.ThingySelectionApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DecisionAdapter.OnItemClickListener {
    private lateinit var decisionList : MutableList<String>
    private lateinit var positionOfDecisionThing: ArrayList<Int>
    private lateinit var adapter: DecisionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        positionOfDecisionThing = ArrayList<Int>()
        decisionList = mutableListOf<String>()
        decisionList.add("Things 1")
        decisionList.add("Things 2")
        decisionList.add("Things 3")
        decisionList.add("Things 4")
        decisionList.add("Things 5")
        decisionList.add("Things 6")
        decisionList.add("Things 7")
        loadDecisionList()
        nextButton.setOnClickListener {
            if(positionOfDecisionThing.size >= 3) {
                var intent = Intent(this, SecondActivity::class.java)
                intent.putIntegerArrayListExtra("count",positionOfDecisionThing)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Minimum three decisions to be selected",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun loadDecisionList() {
         adapter = DecisionAdapter(decisionList, this, this)
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycleView.adapter = adapter
    }

    override fun onClick(view: View?, position: Int) {
        if(!positionOfDecisionThing.contains(position)){
         positionOfDecisionThing.add(position)
        }
    }

    override fun onPause() {
        super.onPause()
        positionOfDecisionThing.clear()
        adapter.notifyDataSetChanged()

    }
}
