package com.example.intencionesimplicitas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class ProductosPrecioAdapter(
    private val productosList: List<ProductoPrecio>,
    private val onItemClick: (ProductoPrecio) -> Unit
) : RecyclerView.Adapter<ProductosPrecioAdapter.ProductoPrecioViewHolder>() {

    class ProductoPrecioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreProductoPrecio: TextView = itemView.findViewById(R.id.tvNombreProductoPrecio)
        val tvValorProductoPrecio: TextView = itemView.findViewById(R.id.tvValorProductoPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoPrecioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_precio, parent, false)
        return ProductoPrecioViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoPrecioViewHolder, position: Int) {
        val producto = productosList[position]
        holder.tvNombreProductoPrecio.text = producto.nombre
        holder.tvValorProductoPrecio.text = String.format(Locale.getDefault(), "$%.2f", producto.precio)

        holder.itemView.setOnClickListener {
            onItemClick(producto)
        }
    }

    override fun getItemCount(): Int = productosList.size
}