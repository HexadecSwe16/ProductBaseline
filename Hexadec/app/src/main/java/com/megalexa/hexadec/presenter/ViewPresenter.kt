package com.megalexa.hexadec.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hexadec.megalexa.Presenter.adapter.recyclerViewWorkflowAdapter
import com.megalexa.hexadec.presenter.contract.MainContract
import com.megalexa.hexadec.model.HexaDec
import com.megalexa.hexadec.model.HexaDec.Companion.workflowList
import com.megalexa.hexadec.model.HexadecUser
import com.megalexa.hexadec.model.Workflow
import com.megalexa.hexadec.model.block.BlockText
import kotlin.concurrent.thread

class WorkflowPresenter(view: MainContract.WorkflowView): MainContract.WorkflowContract {
    private var view:MainContract.WorkflowView? = view

    override fun onDestroy() {
        this.view = null
    }
    override fun popolateView(context: Context, view: RecyclerView){
        view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?
        val adapter = recyclerViewWorkflowAdapter(HexaDec.workflowList)
        view.adapter = adapter
    }
}