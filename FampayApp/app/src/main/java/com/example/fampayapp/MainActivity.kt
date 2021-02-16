package com.example.fampayapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fampayapp.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getCardGroups("https://run.mocky.io/v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad").observe(this, Observer {
            if(it != null){
                Log.d("Repository", "onCreate: successful call")
            }
        })

    }
}