package com.assignment.ThingySelectionApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_decision_row.view.*


class DecisionAdapter(private val decisionThings: List<String>, private val listener: OnItemClickListener, private val context: Context) : RecyclerView.Adapter<DecisionAdapter.ViewHolder>() {
    private var selectedItem = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecisionAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_decision_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return decisionThings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.decisionTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null ,null ,null)
        holder.bindItems(decisionThings.get(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        fun bindItems(decisionThings: String) {
            itemView.decisionTextView.text = decisionThings
        }

        override fun onClick(view: View?) {
            selectedItem = adapterPosition
            listener.onClick(view, adapterPosition)
            var drawable = ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_circle_outline_24)
            itemView.decisionTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
        }
    }

     interface OnItemClickListener {
        fun onClick(view: View?, position: Int)
    }
}
