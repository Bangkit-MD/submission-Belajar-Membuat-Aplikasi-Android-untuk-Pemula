package com.example.familyguycharacters

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName: TextView = findViewById(R.id.detail_name)
        val tvDesc: TextView = findViewById(R.id.detail_desc)
        val imgPhoto: ImageView = findViewById(R.id.detail_img)
        val tvEpisode: TextView = findViewById(R.id.detail_episode)
        val tvFullName: TextView = findViewById(R.id.full_name)
        val tvAge: TextView = findViewById(R.id.age_detail)
        val tvVoice: TextView = findViewById(R.id.voice_detail)
        val data = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_DATA, Characters::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if(data != null){

            tvName.text = data.name.toString()
            tvDesc.text = data.description.toString()
            tvEpisode.text = data.episode.toString()
            tvFullName.text = data.fullName.toString()
            tvAge.text = data.age.toString()
            tvVoice.text = data.voice.toString()
            Glide.with(this).load(data.photo.toString()).into(imgPhoto)


        }

    }
}