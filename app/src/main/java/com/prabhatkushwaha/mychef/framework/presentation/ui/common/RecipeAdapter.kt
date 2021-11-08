package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.databinding.ItemRecipeBinding

class RecipeAdapter(private val onItemClick: (image: ImageView, recipe: Recipe) -> Unit) :
    PagingDataAdapter<Recipe, RecipeAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }


    inner class MyViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: RecipeAdapter.MyViewHolder, position: Int) {
        val recipe = getItem(position) as Recipe
        holder.binding.image.load(recipe.image_url) {
            placeholder(R.drawable.ic_recipe_image_holder)
        }
        holder.binding.image.transitionName = recipe._id
        holder.binding.title.text = recipe.title

        holder.binding.cvRecipeContainer.setOnClickListener {
            onItemClick.invoke(holder.binding.image, recipe)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.MyViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}
