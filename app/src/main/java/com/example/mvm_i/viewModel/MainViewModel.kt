package com.example.mvm_i.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvm_i.data.local.CreatetaskRequestModel
import com.example.mvm_i.data.local.Task
import com.example.mvm_i.repo.MyRepository

class MainViewModel(val repository: MyRepository) : ViewModel() {

    fun getTasksFromApi()
    {
        repository.getRemoteTasks()
    }

    fun createRemoteTask(createtaskRequestModel: CreatetaskRequestModel)
    {
        repository.createTasksAPI(createtaskRequestModel)
    }

    fun createTask(task :Task)
    {
        repository.addTask(task)
    }
    fun getAllTask():LiveData<List<Task>>
    {
        return repository.getAllTasksFromTable()
    }
    fun editTask(task: Task)
    {
        repository.editTask(task)
    }

    fun deleteTask(task: Task)
    {
        repository.deleteTask(task)
    }
}