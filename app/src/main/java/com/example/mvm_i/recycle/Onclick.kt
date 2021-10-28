package com.example.mvm_i.recycle

import com.example.mvm_i.data.local.Task

interface Onclick {
    fun edit(task: Task)
    fun delete(task: Task)
}