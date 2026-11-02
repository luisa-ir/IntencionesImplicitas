package com.example.intencionesimplicitas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactosAdapter(
    private val contactosList: MutableList<Contacto>,
    private val onItemClick: (Contacto, Int) -> Unit
) : RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder>() {

    class ContactoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreContacto: TextView = itemView.findViewById(R.id.tvNombreContacto)
        val tvTelefonoContacto: TextView = itemView.findViewById(R.id.tvTelefonoContacto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacto, parent, false)
        return ContactoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto = contactosList[position]
        holder.tvNombreContacto.text = contacto.nombre
        holder.tvTelefonoContacto.text = contacto.telefono

        holder.itemView.setOnClickListener {
            onItemClick(contacto, position)
        }
    }

    override fun getItemCount(): Int = contactosList.size

    fun agregarContacto(contacto: Contacto) {
        contactosList.add(contacto)
        notifyItemInserted(contactosList.size - 1)
    }

    fun actualizarContacto(posicion: Int, contactoActualizado: Contacto) {
        if (posicion in 0 until contactosList.size) {
            contactosList[posicion] = contactoActualizado
            notifyItemChanged(posicion)
        }
    }

    fun eliminarContacto(posicion: Int) {
        if (posicion in 0 until contactosList.size) {
            contactosList.removeAt(posicion)
            notifyItemRemoved(posicion)
        }
    }
}