package com.example.mvm_i.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvm_i.data.local.Task

@Dao
interface TaskappDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task)

    @Query("select * from tasks")
    fun getTasks(): LiveData<List<Task>>

    @Update()
    fun updateTask(task: Task)

    @Delete
    fun delete(task: Task)

}