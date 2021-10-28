package com.example.mvm_i.recycle

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.mvm_i.R
import com.example.mvm_i.data.local.Task

class MyAdapter(val context:Context,val list: List<Task>, val onclick: Onclick): Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return (MyHolder(view))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var task=list.get(position)
        holder.setData(task)
        holder.button.setOnClickListener(View.OnClickListener {
            onclick.edit(task)
        })
        holder.button2.setOnClickListener(View.OnClickListener {
            onclick.delete(task)
        })

    }

    override fun getItemCount(): Int {
        return list.size
    }
}