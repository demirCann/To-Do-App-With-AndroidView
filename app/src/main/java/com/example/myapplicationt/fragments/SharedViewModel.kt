package com.example.myapplicationt.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplicationt.data.models.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {

    //
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priorityName: String): Priority {
        return when (priorityName) {
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW
            else -> Priority.LOW
        }
    }

}