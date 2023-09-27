package com.example.walmart.Products.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.walmark.R
import com.example.walmark.databinding.ItemProductBinding
import com.example.walmart.Models.Product
import com.squareup.picasso.Picasso

class ProductsAdaptersList(private val listTrainers: List<Product>, val context: Context,) : RecyclerView.Adapter<ProductsAdaptersList.ProductsAdaptersListHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdaptersListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsAdaptersListHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int = listTrainers.size


    override fun onBindViewHolder(holder: ProductsAdaptersListHolder, position: Int) {

        val item = listTrainers[position]
        holder.bind(item,position,context)

    }


    class ProductsAdaptersListHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemProductBinding.bind(view)

        fun bind(product: Product, position: Int, context: Context){

            binding.textName.text = product.title
            binding.textPrices.text = "$ "+product.price

            Picasso.get().load(product.image)
                .placeholder(R.drawable.ic_placeholder)
                .resize(600, 600)
                .into(binding.imgProduct)

        }
    }
}