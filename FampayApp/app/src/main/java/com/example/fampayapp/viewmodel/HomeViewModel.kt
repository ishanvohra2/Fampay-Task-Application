package com.example.fampayapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fampayapp.model.APIResponse
import com.example.fampayapp.model.CardGroup
import com.example.fampayapp.util.Repository

class HomeViewModel : ViewModel(){

    fun getCardGroups() : LiveData<APIResponse>{
        return Repository.getCardGroups()
    }

}