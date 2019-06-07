package com.megalexa.hexadec.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.megalexa.hexadec.R
import com.megalexa.hexadec.view.activity.SettingsActivity
import kotlinx.android.synthetic.main.workflow_view.my_toolbar

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("Registered")
class ConfigurationBlocks: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Calcio", "Notizie"))

       var name = intent.getStringExtra("name")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( item.itemId == R.id.settings) {
            startActivity(Intent(this@ConfigurationBlocks, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onStart() {
        super.onStart()
          var TextV = findViewById<TextView>(R.id.textViewWorkflowName)
          val prova = intent.extras.getString("workflowCorrente")
    }
}