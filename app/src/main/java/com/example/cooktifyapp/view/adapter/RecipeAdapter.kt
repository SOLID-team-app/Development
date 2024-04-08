package com.example.cooktifyapp.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooktifyapp.databinding.ItemsRecipeBinding
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipe

class RecipeAdapter: ListAdapter<ResponseRecipe, RecipeAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(val binding: ItemsRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseRecipe) {
            binding.tvMakananFav.text = item.namaMakanan
            binding.tvIngredients.text = item.bahanUtama
            Glide.with(itemView.context)
                .load(item.linkGambar)
                .into(binding.imgFav)

//            binding.userItem.setOnClickListener {
//                val intent = Intent(itemView.context, DetailItems::class.java)
//                intent.putExtra(DetailItems.ITEM_IMAGE_URL, item.imageUrl)
//                intent.putExtra(DetailItems.ITEM_CLOTHING_TYPE, item.clothingType)
//                itemView.context.startActivity(intent)
//            }
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseRecipe>() {
            override fun areItemsTheSame(oldItem: ResponseRecipe, newItem: ResponseRecipe): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResponseRecipe, newItem:ResponseRecipe): Boolean {
                return oldItem == newItem
            }
        }
    }


}