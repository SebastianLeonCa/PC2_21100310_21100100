package dev.sebastianleon.firebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.sebastianleon.firebase.R
import dev.sebastianleon.firebase.model.ResumenModel

class ResumenAdapter(private var lstResumen: List<ResumenModel>):
    RecyclerView.Adapter<ResumenAdapter.ViewHolder>(){

        class ViewHolder(view: View): RecyclerView.ViewHolder(view)
        {
            val tvIngresos: TextView = view.findViewById<TextView>(R.id.tvIngresos)
            val tvGastos: TextView = view.findViewById<TextView>(R.id.tvGastos)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_resumen, parent, false))
    }

    override fun getItemCount(): Int {
        return lstResumen.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemResumen = lstResumen[position]
        holder.tvIngresos.text = itemResumen.ingreso
        holder.tvGastos.text = itemResumen.costo
    }
}