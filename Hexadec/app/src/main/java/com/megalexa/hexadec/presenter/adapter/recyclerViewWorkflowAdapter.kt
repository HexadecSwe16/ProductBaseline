package com.hexadec.megalexa.Presenter.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//import android.R
import android.widget.TextView

import com.megalexa.hexadec.R
import com.megalexa.hexadec.model.Workflow
import com.megalexa.hexadec.view.activity.ConfigurationWorkflow
import kotlinx.android.synthetic.main.workflow_item.view.*

class recyclerViewWorkflowAdapter (val workflowList: ArrayList<Workflow>) : RecyclerView.Adapter<recyclerViewWorkflowAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      //creaiamo la nostra view holder
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.workflow_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workflowList.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workflow: Workflow = workflowList[position]
        holder?.textViewName?.text = workflow.getWorkflowName()
        holder?.textViewDate?.text = workflow.getCreationDate()


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textViewName =  itemView.findViewById<TextView>(R.id.textViewWorkflowName) as TextView
        val textViewDate =  itemView.findViewById<TextView>(R.id.textViewWorkflowCreateDate) as TextView

        init{
            itemView.setOnClickListener{
               val intent = Intent(itemView.context, ConfigurationWorkflow::class.java)
                intent.putExtra("workflowCorrente", itemView.textViewWorkflowName.toString())
                itemView.context.startActivity(intent)

            }
        }

}
}