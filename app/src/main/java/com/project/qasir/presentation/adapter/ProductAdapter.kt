package com.project.qasir.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.qasir.R
import com.project.qasir.common.shared.ImageHelper
import com.project.qasir.data.remote.model.ProductItem
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val itemClickListener: (Any) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: ArrayList<Any> = ArrayList()
    private var products: List<ProductItem> = ArrayList()

    fun setProducts(productList: List<ProductItem>) {
        this.products = productList
        mergeAndNotifyData()
    }

    private fun mergeAndNotifyData() {
        data.clear()
        data.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = ProductViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_product,
                        parent,
                        false
                )
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position >= 0 && position <= data.size && data.isNotEmpty()) {
                val data = data[position]
                itemClickListener(data)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = data[position]
        (holder as ProductViewHolder).bind(data as ProductItem)
    }

    override fun getItemCount(): Int = data.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProductItem) {
            ImageHelper.setResource(item.images.thumbnail, itemView.iv_icon_product)
            itemView.tv_title_product.text = item.product_name
            itemView.tv_price_product.text = item.price.toString()
            itemView.tv_quantity_product.text = item.stock.toString()
        }
    }

}