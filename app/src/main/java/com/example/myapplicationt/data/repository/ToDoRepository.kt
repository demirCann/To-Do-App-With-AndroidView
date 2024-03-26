package com.example.myapplicationt.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplicationt.data.ToDoDao
import com.example.myapplicationt.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }


    suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData)
    }

}