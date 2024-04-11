package com.example.cooktifyapp.view.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooktifyapp.databinding.ItemsRecipeBinding
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem
import com.example.cooktifyapp.view.detail.Detail

class RecipeAdapter: ListAdapter<ResponseRecipesItem, RecipeAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(val binding: ItemsRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseRecipesItem) {
            binding.tvMakananresep.text = item.namaMakanan
            binding.tvIngredients.text = item.bahanUtama
            Glide.with(itemView.context)
                .load(item.linkGambar)
                .into(binding.imgResep)

            binding.itemsOutput.setOnClickListener {
                val intent = Intent(itemView.context, Detail::class.java)
                intent.putExtra(Detail.ITEM_IMAGE_URL, item.linkGambar)
                intent.putExtra(Detail.ITEM_BAHAN_UTAMA, item.bahanUtama)
                itemView.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.MyViewHolder {
        val binding = ItemsRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.MyViewHolder, position: Int) {
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