package com.example.iotkebakaran.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.iotkebakaran.R
import com.example.iotkebakaran.models.Kebakaran
import com.example.iotkebakaran.ui.info.InfoFragment

class KebakaranRecyclerViewAdapter(private val list: List<Kebakaran>) : RecyclerView.Adapter<KebakaranRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_kebakaran, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rowKebakaranAlamatTextView.text = list[position].info.alamat
        holder.rowKebakaranJamTextView.text = list[position].info.waktu
        holder.rowKebakaranTanggalTextView.text = list[position].info.tanggal

        holder.itemView.setOnClickListener{ view ->
            val bundle = Bundle()
            bundle.putString("id", list[position].id)
            view.findNavController().navigate(R.id.action_BerandaFragment_to_infoFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val rowKebakaranAlamatTextView: TextView = itemView.findViewById(R.id.rowKebakaranAlamatTextView)
        val rowKebakaranJamTextView: TextView = itemView.findViewById(R.id.rowKebakaranJamTextView)
        val rowKebakaranTanggalTextView: TextView = itemView.findViewById(R.id.rowKebakaranTanggalTextView)
    }
}