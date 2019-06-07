package com.megalexa.hexadec.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.megalexa.hexadec.R
import com.megalexa.hexadec.model.Workflow
import com.megalexa.hexadec.presenter.BlockPresenter
import com.megalexa.hexadec.presenter.contract.MainContract
import com.megalexa.hexadec.view.AddBlocksActivity
import kotlinx.android.synthetic.main.filterview.*
import kotlinx.android.synthetic.main.workflow_view.my_toolbar


class FilterBlockActivity : AppCompatActivity(),MainContract.BlockView {


    internal lateinit var presenter: MainContract.BlockContract
    private lateinit var newWorkflow : Workflow

    override fun setPresenter(presenter: MainContract.BlockContract) {
       this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filterview)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(getString(R.string.app_name))
        setPresenter(BlockPresenter(this@FilterBlockActivity))
        filterBlockbtnSalvab.setOnClickListener {
            val intent=Intent(this@FilterBlockActivity, AddBlocksActivity::class.java)
            intent.putExtra("Workflow",newWorkflow)
            startActivity(intent)
        }
        filterBlockbtnSalvaw.setOnClickListener {
            newWorkflow=presenter.addBlock(newWorkflow,findViewById<EditText>(R.id.textBlockConf).text.toString(),"FILTER")
            presenter.saveWorkflow(newWorkflow)
            startActivity(Intent(this@FilterBlockActivity, ViewActivity::class.java))
        }
        filterBlockbtnAnnulla.setOnClickListener {
            val intent=Intent(this@FilterBlockActivity, NewWorkflowActivity::class.java)
            intent.putExtra("Workflow",newWorkflow)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( item.itemId == R.id.settings) {
            startActivity(Intent(this@FilterBlockActivity, FilterBlockActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}