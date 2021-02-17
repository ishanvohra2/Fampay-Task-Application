package com.example.fampayapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fampayapp.R
import com.example.fampayapp.model.Card


class HC1Adapter : RecyclerView.Adapter<HC1Adapter.MyViewHolder>() {

    lateinit var context: Context
    lateinit var cards: List<Card>

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTv: TextView = itemView.findViewById(R.id.hc1_title_tv)
        val iconIv: ImageView = itemView.findViewById(R.id.hc1_profile_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_hc1, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card = cards.get(position)

        holder.titleTv.setText(card.title)

        Glide.with(context)
            .load(card.icon.image_url)
            .into(holder.iconIv)

        if(card.url != null){
            holder.itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(card.url))
                context.startActivity(browserIntent)
            }
        }
    }
}