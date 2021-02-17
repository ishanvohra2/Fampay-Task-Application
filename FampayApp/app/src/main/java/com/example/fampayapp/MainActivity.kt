package com.example.fampayapp

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fampayapp.adapter.*
import com.example.fampayapp.model.Card
import com.example.fampayapp.model.CardGroup
import com.example.fampayapp.viewmodel.HomeViewModel
import java.util.*

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
                    if(cardGroup.design_type == "HC3"){
                        inflateHC3(cardGroup)
                    }
                }

                for(cardGroup in response.card_groups){
                    if(cardGroup.design_type == "HC6"){
                        inflateHC6(cardGroup)
                    }
                }

                for(cardGroup in response.card_groups){
                    if(cardGroup.design_type == "HC5"){
                        inflateHC5(cardGroup)
                    }
                }

                for(cardGroup in response.card_groups){
                    if(cardGroup.design_type == "HC9"){
                        inflateHC9(cardGroup)
                    }
                }

                for(cardGroup in response.card_groups){
                    if(cardGroup.design_type == "HC1"){
                        inflateHC1(cardGroup)
                    }
                }
            }
        })
    }

    private fun inflateHC1(cardGroup: CardGroup) {
        val recyclerView = RecyclerView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 0)

        recyclerView.layoutParams = params
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val hC1Adapter = HC1Adapter()
        hC1Adapter.context = this
        hC1Adapter.cards = cardGroup.cards

        recyclerView.adapter = hC1Adapter

        linearLayout.addView(recyclerView)
    }

    private fun inflateHC9(cardGroup: CardGroup) {
        val recyclerView = RecyclerView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 0)

        recyclerView.layoutParams = params
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val hC9Adapter = HC9Adapter()
        hC9Adapter.context = this
        hC9Adapter.cards = cardGroup.cards

        recyclerView.adapter = hC9Adapter

        linearLayout.addView(recyclerView)
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

    private fun inflateHC6(cardGroup: CardGroup){
        val recyclerView = RecyclerView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 0)

        recyclerView.layoutParams = params
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val hC6Adapter = HC6Adapter()
        hC6Adapter.context = this
        hC6Adapter.cards = cardGroup.cards

        recyclerView.adapter = hC6Adapter

        linearLayout.addView(recyclerView)
    }

    private fun inflateHC5(cardGroup: CardGroup){
        val recyclerView = RecyclerView(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 0)

        recyclerView.layoutParams = params
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val hC5Adapter = HC5Adapter()
        hC5Adapter.context = this
        hC5Adapter.cards = cardGroup.cards

        recyclerView.adapter = hC5Adapter

        linearLayout.addView(recyclerView)
    }
}