package com.example.mvm_i.repo

import android.net.Network
import androidx.lifecycle.LiveData
import com.example.mvm_i.data.local.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyRepository(val taskappDAO: TaskappDAO) {

    private val retrofitApi=com.example.mvm_i.data.local.Network.getRetrofit()
        .create(TasksApi::class.java)
    private val responseHandler=ResponseHandler()
    private val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTRjYzk1NGYwNDg1NDBiNWMxNDkzNzciLCJpYXQiOjE2MzUzMzI2NTl9.diTLyDE-b52k3SQ5iQJKYS0WrYDR9T3V2mRlxhedyo8"

    fun getRemoteTasks()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val respose=retrofitApi.getTasksFromAPI(token)
            saveToDb(respose)
        }
    }

    fun createTasksAPI(createtaskRequestModel: CreatetaskRequestModel) {
        CoroutineScope(Dispatchers.IO).launch {
            retrofitApi.createTask(token,createtaskRequestModel)

            addTask(Task(createtaskRequestModel.title,createtaskRequestModel.description))
        }
    }


    private fun saveToDb(respose: GetTasksResponseModel) {
        respose.forEach {
            val task=Task(it.title,it.description)
            addTask(task)
        }
    }

    fun addTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskappDAO.addTask(task) }
    }

    fun getAllTasksFromTable() :LiveData<List<Task>>{
       return taskappDAO.getTasks()
    }
    fun editTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskappDAO.updateTask(task) }
    }
    fun deleteTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskappDAO.delete(task) }
    }

}