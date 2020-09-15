package com.example.get_api.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.get_api.Model.LanguageModel
import com.example.get_api.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class Adapter(
    var context: Context,
    var responseModel: ArrayList<LanguageModel>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return responseModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.language_id.setText("" +responseModel.get(position).LanguageId)
        holder.itemView.language_name.setText(responseModel.get(position).Language)
        Log.d("Size", "onBindViewHolder: "+ responseModel.size)
    }

}