package com.androiddevs.ucanbit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.ui.models.CoinsResponseItem
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
            tvSource.text = coin.name
            tvTitle.text = coin.id
            tvDescription.text = coin.price_change_24h.toString()
            tvPublishedAt.text = coin.current_price.toString()
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