package com.kultur.futbolfiksturuygulamasi.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kultur.futbolfiksturuygulamasi.Model.DataModel
import com.kultur.futbolfiksturuygulamasi.R
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private  val dataList : ArrayList<DataModel>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(){


    interface Listener{
        fun onItemClick(dataModel: DataModel)
    }

    class RowHolder(view : View) :RecyclerView.ViewHolder(view){

        var layoutBackground: LinearLayout?=null
        var img: ImageView?=null

        @SuppressLint("SetTextI18n", "ResourceType")
        fun bind(dataModel:DataModel, position: Int, listener: Listener, dataList: ArrayList<DataModel>){

            itemView.setOnClickListener {
                listener.onItemClick(dataModel)
            }
            itemView.ilkTakim.text=dataModel.takim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(dataList[position],position,listener, dataList)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }
}