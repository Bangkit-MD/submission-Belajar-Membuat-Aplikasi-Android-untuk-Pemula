package com.example.familyguycharacters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide

class ListCharAdapter(private val listChar: ArrayList<Characters>): RecyclerView.Adapter<ListCharAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPhoto: ImageView = itemView.findViewById(R.id.character_card_image)
        val tvName: TextView = itemView.findViewById(R.id.tv_card_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_card_desc)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {



        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_card_character,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listChar.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listChar[position]
        holder.tvName.text = name
        holder.tvDesc.text = description
        Glide.with(holder.itemView.context).load(photo).into(holder.imgPhoto)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listChar[holder.adapterPosition]) }

    }
    interface OnItemClickCallback{
        fun onItemClicked(data: Characters)
    }
}