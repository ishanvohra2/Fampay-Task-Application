package com.example.fampayapp

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fampayapp.adapter.HC3Adapter
import com.example.fampayapp.model.Card
import com.example.fampayapp.model.CardGroup
import com.example.fampayapp.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel
    lateinit var context: Context
    lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        linearLayout = findViewById(R.id.container_layout)

        viewModel.getCardGroups()!!.observe(this, Observer {response ->
            if(response != null){
                for(cardGroup in response.card_groups){
                    if(cardGroup.design_type.equals("HC3")){
                        inflateHC3(cardGroup)
                    }
                }
            }
        })
    }

    private fun inflateHC3(cardGroup: CardGroup) {
        val recyclerView = RecyclerView(this)
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 0)

        recyclerView.layoutParams = params
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val hC3Adapter = HC3Adapter()
        hC3Adapter.context = this
        hC3Adapter.cards = cardGroup.cards

        recyclerView.adapter = hC3Adapter

        linearLayout.addView(recyclerView)
    }
}