package com.example.fampayapp

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fampayapp.adapter.*
import com.example.fampayapp.model.Card
import com.example.fampayapp.model.CardGroup
import com.example.fampayapp.viewmodel.HomeViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import java.util.*

class MainActivity : AppCompatActivity(), HC3Adapter.onOptionsSelectListener {

    lateinit var viewModel: HomeViewModel
    lateinit var context: Context

    lateinit var linearLayout: LinearLayout
    lateinit var shimmerLayout: ShimmerFrameLayout

    var dismissed: Boolean = false
    var remindLater: Boolean = false

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        linearLayout = findViewById(R.id.container_layout)
        shimmerLayout = findViewById(R.id.shimmer)
        shimmerLayout.startShimmer()

        val swipeRefreshLayout : SwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {
            shimmerLayout.startShimmer()
            shimmerLayout.visibility = View.VISIBLE
            linearLayout.visibility = View.GONE

            swipeRefreshLayout.isRefreshing = false
            loadData()
        }

        sharedPreferences = this.getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
        dismissed = sharedPreferences.getBoolean("dismissed", false)

        loadData()
    }

    private fun loadData(){
        viewModel.getCardGroups()!!.observe(this, Observer {response ->
            linearLayout.removeAllViews()
            shimmerLayout.stopShimmer()
            shimmerLayout.visibility = View.GONE
            linearLayout.visibility = View.VISIBLE

            if(response != null){
                if(!remindLater && !dismissed){
                    for(cardGroup in response.card_groups){
                        if(cardGroup.design_type == "HC3"){
                            inflateHC3(cardGroup)
                        }
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
            else{
                Toast.makeText(this, "Error, please try again", Toast.LENGTH_LONG).show()
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
        recyclerView.id = R.id.hc3
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
        hC3Adapter.listener = this

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

    override fun onRemindLaterSelected() {
        remindLater = true
        val recyclerView: RecyclerView = findViewById(R.id.hc3)
        recyclerView.visibility = View.GONE
    }

    override fun onDismissNowSelected() {
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putBoolean("dismissed", true)
        editor.apply()

        dismissed = true

        val recyclerView: RecyclerView = findViewById(R.id.hc3)
        recyclerView.visibility = View.GONE
    }
}