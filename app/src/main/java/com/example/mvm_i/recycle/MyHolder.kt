package com.example.mvm_i.recycle

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvm_i.data.local.Task
import kotlinx.android.synthetic.main.item.view.*

class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var button=itemView.edit
    var button2=itemView.delete
     fun setData(task: Task)
     {
         itemView.tittle.text=task.title
         itemView.desc.text=task.desc
     }
}