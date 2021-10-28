package com.example.mvm_i.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvm_i.R
import com.example.mvm_i.data.local.CreatetaskRequestModel
import com.example.mvm_i.data.local.Task
import com.example.mvm_i.data.local.TaskRoomDatabase
import com.example.mvm_i.data.local.TaskappDAO
import com.example.mvm_i.recycle.MyAdapter
import com.example.mvm_i.recycle.Onclick
import com.example.mvm_i.repo.MyRepository
import com.example.mvm_i.viewModel.MainViewModel
import com.example.mvm_i.viewModel.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,Onclick{
    lateinit var viewModel : MainViewModel
    lateinit var repository : MyRepository
    lateinit var taskappDAO: TaskappDAO
    var list= mutableListOf<Task>()
    lateinit var recyclerView:RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       taskappDAO = TaskRoomDatabase.getDatabaseObject(this).getTaskDAO()
        repository = MyRepository(taskappDAO)

        val viewModelFactory=MyViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)

        //create remote task
        val createtaskRequestModel=CreatetaskRequestModel("Remote",
        0,"remote tittle")
        viewModel.createRemoteTask(createtaskRequestModel)

        //create local task
        val task=Task("tittle","Desc")
        viewModel.createTask(task)
        viewModel.createTask(task)
        viewModel.createTask(task)
        viewModel.createTask(task)
        viewModel.createTask(task)

        //get tasks fromapi
        viewModel.getTasksFromApi()
        viewModel.getAllTask().observe(this,{
            var data=""
            it.forEach {
                data+="\n"+it.title
                var task=it
                list.add(it)
            }
            taskTv.text=data
        })
        recyclerView=recycler
        myAdapter= MyAdapter(this,list,this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=myAdapter
    }

    override fun edit(task: Task) {
        val newTitle = "New title"
        val newDesc = "New Desc"
        task.title = newTitle
        task.desc = newDesc

        viewModel.editTask(task)
        viewModel.getAllTask().observe(this, Observer {
            list.clear()
            list.addAll(it)
            myAdapter.notifyDataSetChanged()
        })
    }

    override fun delete(task: Task) {
        viewModel.deleteTask(task)
        viewModel.getAllTask().observe(this, Observer {
            list.clear()
            list.addAll(it)
            myAdapter.notifyDataSetChanged()
        })
    }

}