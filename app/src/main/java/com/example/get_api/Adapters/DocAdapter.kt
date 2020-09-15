package com.example.get_api.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.get_api.Model.DocModel
import com.example.get_api.R
import kotlinx.android.synthetic.main.doc_item_layout.view.*

class DocAdapter(var context: Context, var docModel: ArrayList<DocModel>) :
    RecyclerView.Adapter<DocAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doc_item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return docModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.doc_id.setText(""+ docModel.get(position).DOCCategoryId)
        holder.itemView.doc_category.setText(docModel.get(position).DOCCategory)
    }
}