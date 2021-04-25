package com.androiddevs.ucanbit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.models.CoinsResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*

class CoinsAdapter: RecyclerView.Adapter<CoinsAdapter.CoinViewHolder>() {

    inner class CoinViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

        private val differCallback = object : DiffUtil.ItemCallback<CoinsResponseItem>() {
            override fun areItemsTheSame(oldItem: CoinsResponseItem, newItem: CoinsResponseItem): Boolean {
                return oldItem.last_updated == newItem.last_updated
            }

            override fun areContentsTheSame(oldItem: CoinsResponseItem, newItem: CoinsResponseItem): Boolean {
                return oldItem == newItem
            }
        }

        val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(coin.image).into(ivArticleImage)
            tvSymbol.text = coin.symbol
            tvCoin.text = coin.name
            tvValue.text = String.format("%.2f",coin.current_price) + " US$"
            if(coin.price_change_24h < 0){
                tvVariation.setTextColor(resources.getColor((R.color.colorDown)))
                tvPercentaje.setTextColor(resources.getColor(R.color.colorDown))
            }else{
                tvVariation.setTextColor(resources.getColor(R.color.colorUP))
                tvPercentaje.setTextColor(resources.getColor(R.color.colorUP))
            }

            tvVariation.text = String.format("%.2f",coin.price_change_24h)
            tvPercentaje.text =  "( " + String.format("%.2f" ,coin.price_change_percentage_24h)  + " %)"

            setOnItemClickListener {
                onItemClickListener?.let { it(coin) }
            }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((CoinsResponseItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (CoinsResponseItem) -> Unit) {
        onItemClickListener = listener
    }


}