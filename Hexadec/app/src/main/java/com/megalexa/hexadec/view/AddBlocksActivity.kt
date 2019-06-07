
package com.megalexa.hexadec.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.megalexa.hexadec.R
import com.megalexa.hexadec.model.Workflow

import com.megalexa.hexadec.presenter.BlockListAdapter
import com.megalexa.hexadec.view.activity.BlockActivity
import com.megalexa.hexadec.view.activity.FeedRssBlockActivity
import com.megalexa.hexadec.view.activity.FilterBlockActivity
import com.megalexa.hexadec.view.activity.SettingsActivity
import kotlinx.android.synthetic.main.addblock_gridview.*
import kotlinx.android.synthetic.main.workflow_view.my_toolbar


class AddBlocksActivity: AppCompatActivity() {

    private lateinit var newWorkflow:Workflow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addblock_gridview)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val gridView = findViewById<GridView>(R.id.gridview)
        val arrayListImage = ArrayList<Int>()

        arrayListImage.add(R.drawable.text_icon2)
        arrayListImage.add(R.drawable.feedrss_icon)
        arrayListImage.add(R.drawable.filter_icon)
        arrayListImage.add(R.drawable.instagram_icon)
        newWorkflow  = intent.getSerializableExtra("Workflow") as Workflow
        val name = arrayOf("Testo", "FeedRSS", "Fitro", "Instagram")
        val customAdapter = BlockListAdapter(this@AddBlocksActivity, arrayListImage, name)
        gridView.adapter = customAdapter
        gridview.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0-> {
                        val intentText= Intent(this@AddBlocksActivity, BlockActivity::class.java)
                        intentText.putExtra("WorkFlow",newWorkflow)
                        startActivity(intentText)
                    }
                    1->{
                        val intentFeed= Intent(this@AddBlocksActivity, FeedRssBlockActivity::class.java)
                        intentFeed.putExtra("WorkFlow",newWorkflow)
                        startActivity(intentFeed)
                    }
                    2->{
                        val intentFilter= Intent(this@AddBlocksActivity, FilterBlockActivity::class.java)
                        intentFilter.putExtra("WorkFlow",newWorkflow)
                        startActivity(intentFilter)
                    }
                    //3->  startActivity(Intent(this@AddBlocksActivity, InstagramBlockActivity::class.java))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( item.itemId == R.id.settings) {
            startActivity(Intent(this@AddBlocksActivity, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        if( intent.hasExtra("Workflow")) {
            newWorkflow = intent.getSerializableExtra("Workflow") as Workflow
            Log.d("workflow Errore","OKIntent")
        }
        else{
            // Log.d("workflow Errore",intent.getStringExtra("Workflow").toString())
            Log.d("workflow Errore","workflow vuotoAddA")
        }
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}