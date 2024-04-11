package com.example.cooktifyapp.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityDetailBinding
import com.example.cooktifyapp.databinding.ItemsRecipeBinding
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem

class DetailAdapter: ListAdapter<ResponseRecipesItem, DetailAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(val binding: ActivityDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseRecipesItem) {
            binding.tvBahanUtama.text = item.bahanUtama
            Glide.with(itemView.context)
                .load(item.linkGambar)
                .placeholder(R.drawable.tulisan_cooktify)
                .into(binding.ivImage)

//            binding.userItem.setOnClickListener {
//                val intent = Intent(itemView.context, DetailItems::class.java)
//                intent.putExtra(DetailItems.ITEM_IMAGE_URL, item.imageUrl)
//                intent.putExtra(DetailItems.ITEM_CLOTHING_TYPE, item.clothingType)
//                itemView.context.startActivity(intent)
//            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.MyViewHolder {
        val binding = ActivityDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailAdapter.MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseRecipesItem>() {
            override fun areItemsTheSame(oldItem: ResponseRecipesItem, newItem: ResponseRecipesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResponseRecipesItem, newItem:ResponseRecipesItem): Boolean {
                return oldItem == newItem
            }
        }
    }


}