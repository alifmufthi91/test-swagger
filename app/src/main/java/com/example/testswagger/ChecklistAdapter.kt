package com.example.testswagger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_checklist.view.*

class ChecklistAdapter : RecyclerView.Adapter<ChecklistAdapter.ChecklistViewholder>() {

    var currentList = ArrayList<ChecklistItem>()

    fun setData(list :ArrayList<ChecklistItem>){
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ChecklistViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(checklistItem: ChecklistItem){
            with(itemView){
                tv_id.text = "ID: "+checklistItem.id.toString()
                tv_name.text = checklistItem.name
                tv_status.text = "Status : ${checklistItem.status.toString()}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewholder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.items_checklist,parent, false)
        return ChecklistViewholder(view)
    }

    override fun onBindViewHolder(holder: ChecklistViewholder, position: Int) {
        currentList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = currentList.size
}