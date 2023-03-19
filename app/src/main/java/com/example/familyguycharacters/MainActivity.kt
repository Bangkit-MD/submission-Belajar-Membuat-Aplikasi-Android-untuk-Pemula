package com.example.familyguycharacters

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvChar: RecyclerView
    private val list = ArrayList<Characters>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         rvChar = findViewById(R.id.rv_character)
        rvChar.setHasFixedSize(true)

        list.addAll(getListCharacters())
        showRecycleViewList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about){

            val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(aboutIntent)

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        return super.onMenuOpened(featureId, menu)
    }

    private fun showSelectedChar(char: Characters){
        Toast.makeText(this, "You Selected" + char.name, Toast.LENGTH_SHORT).show()

    }

    private fun getListCharacters(): ArrayList<Characters>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataPhoto = resources.getStringArray(R.array.data_img)
        val dataEpisode = resources.getStringArray(R.array.data_episode)
        val dataFullName = resources.getStringArray(R.array.data_full_name)
        val dataAge = resources.getStringArray(R.array.data_age)
        val dataVoice = resources.getStringArray(R.array.data_voice)
        val listCharacter = ArrayList<Characters>()
        for(i in dataName.indices){
            val character = Characters(dataName[i],dataDesc[i],dataPhoto[i],dataEpisode[i],dataFullName[i], dataAge[i], dataVoice[i] )
            listCharacter.add(character)
        }
        return listCharacter
    }
    private fun showRecycleViewList(){
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvChar.layoutManager = GridLayoutManager(this, 3)
        } else {
            rvChar.layoutManager = LinearLayoutManager(this)
        }
        val listCharAdapter = ListCharAdapter(list)
        rvChar.adapter = listCharAdapter

        listCharAdapter.setOnItemClickCallback(object : ListCharAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Characters) {
                val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentDetail.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intentDetail)
//                showSelectedChar(data)
            }
        })
    }
}