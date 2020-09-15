package com.example.get_api.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.get_api.Model.NulmModel
import com.example.get_api.R
import kotlinx.android.synthetic.main.nulm_item_layout.view.*

class NulmAdapter(var context: Context, var nulmModel: ArrayList<NulmModel>) :
    RecyclerView.Adapter<NulmAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.nulm_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nulmModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.nulm_id.setText("" + nulmModel.get(position).NULMCategoryId)
        holder.itemView.nulm_category.setText(nulmModel.get(position).NULMCategory)
        holder.itemView.nulm_category_ff.setText(nulmModel.get(position).NULMCategoryFF)
        holder.itemView.color_code.setText(nulmModel.get(position).ColorCode)

    }
}