package com.example.intencionesimplicitas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotasAdapter(
    private val notasList: MutableList<Nota>,
    private val onItemClick: (Nota) -> Unit
) : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>() {

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTituloNota: TextView = itemView.findViewById(R.id.tvTituloNota)
        val tvContenidoNota: TextView = itemView.findViewById(R.id.tvContenidoNota)
        val tvFechaNota: TextView = itemView.findViewById(R.id.tvFechaNota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notasList[position]
        holder.tvTituloNota.text = nota.titulo
        holder.tvContenidoNota.text = nota.contenido
        holder.tvFechaNota.text = nota.fecha

        holder.itemView.setOnClickListener {
            onItemClick(nota)
        }
    }

    override fun getItemCount(): Int = notasList.size

    fun agregarNota(nota: Nota) {
        notasList.add(0, nota)
        notifyItemInserted(0)
    }
}