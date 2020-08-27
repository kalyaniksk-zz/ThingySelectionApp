package com.assignment.ThingySelectionApp

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.second_layout.*
import java.util.*


class SecondActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val extras = intent.extras
        val decisionCount = extras?.getIntegerArrayList("count")
        val randomDecision = decisionCount?.random()
        decisionCount?.remove(randomDecision)
        if (decisionCount != null) {
            createTextView(decisionCount)

        }

        if(randomDecision != null){
            //show chosin decision thing
            createRandomChosenTextView(randomDecision)


        }
        backButton.setOnClickListener {
            decisionCount?.clear()
            finish()
        }
    }
    private fun createTextView(decisionCount: ArrayList<Int>) {
        for(i in decisionCount){
            val textView = TextView(this)
            textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            (textView.layoutParams as LinearLayout.LayoutParams).setMargins(10,10,10,10)
            textView.text = "Thing: " + (i + 1)
            textView.gravity = Gravity.CENTER
            textView.setBackgroundResource(R.drawable.round_bottom)
            textView.setPadding(20,20,20,20)
            val anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_anim)
            textView.startAnimation(anim)
            selectionDecisionContainer.addView(textView)
        }
    }
    private fun createRandomChosenTextView(randomDecision: Int){
        val textView = TextView(this)

        textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        (textView.layoutParams as LinearLayout.LayoutParams).setMargins(10,10,10,10)
        textView.text ="Chosen Thing:\n\n"+ "Thing" + (randomDecision + 1)
        textView.gravity = Gravity.CENTER
        textView.setBackgroundResource(R.drawable.round_bottom)
        textView.setPadding(10,10,10,10)
        chosenRandomDecisionContainer.addView(textView)
        val anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_anim)
        textView.startAnimation(anim)
    }
}