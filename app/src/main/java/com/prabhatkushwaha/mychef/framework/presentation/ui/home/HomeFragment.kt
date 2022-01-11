package com.prabhatkushwaha.mychef.framework.presentation.ui.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.RecyclerView
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.databinding.FragmentHomeBinding
import com.prabhatkushwaha.mychef.databinding.ItemCategoryBinding
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.NetworkStateAdapter
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import android.graphics.drawable.BitmapDrawable
import com.prabhatkushwaha.mychef.framework.presentation.ui.views.UiUtils.Companion.getBase64


@ExperimentalPagingApi
@InternalCoroutinesApi
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeFragment(viewModelFactory: ViewModelProvider.Factory) :
    BaseFragment<FragmentHomeBinding>() {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }
    private val adapter: RecipeAdapter by lazy {
        RecipeAdapter() { image, recipe ->
            val extras = FragmentNavigatorExtras(
                image to "RECIPE_TRANSITION"
            )
            val drawable = image.drawable as BitmapDrawable
            val bitmap: Bitmap = drawable.bitmap
            val action =
                HomeFragmentDirections.actionHomeFragmentToRecipeDetailsFragment(
                    id = recipe.recipe_id,
                    recipeImage = bitmap.getBase64()
                )
            navController?.navigate(action, extras)

        }
    }

    override fun subscribeObservers() {
        viewModel.recipe.observe(viewLifecycleOwner) {
            hideProgressDialog()
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.hideShimmer()
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }


    private fun initAdapter() {
        binding.rvRecipe.adapter = adapter.withLoadStateFooter(
            NetworkStateAdapter {
                adapter.retry()
            })

        startNewSearch()

    }

    private fun startNewSearch() {
        showProgressDialog()
        viewModel.getRecipe()
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }


    override fun initialize() {
        viewModel.setupChannel()
        setupCategoryAdapter()
        setupSwipeRefreshView()
        initAdapter()
    }


    private fun setupCategoryAdapter() {
        val list = resources.getStringArray(R.array.category_array)
        val adapter = CategoryAdapter(list) { item ->
            viewModel.setQuery(item)
            startNewSearch()
        }
        binding.rvCategory.adapter = adapter
    }

    private fun setupSwipeRefreshView() {
        binding.swipeToRefresh.setOnRefreshListener {

            binding.swipeToRefresh.isRefreshing = false
        }
    }


    class CategoryAdapter(
        private val categoryList: Array<String>, private val itemClick: (item: String) -> Unit
    ) : RecyclerView.Adapter<CategoryAdapter.CategoryViewModel>() {
        private var selectedItemPosition = 0

        inner class CategoryViewModel(val binding: ItemCategoryBinding) :
            RecyclerView.ViewHolder(binding.root)


        override fun onCreateViewHolder(parent: ViewGroup, position: Int): CategoryViewModel {
            val binding = ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CategoryViewModel(
                binding
            )
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        override fun onBindViewHolder(
            holder: CategoryViewModel,
            @SuppressLint("RecyclerView") position: Int
        ) {

            with(holder) {
                val context = binding.btCategory.context
                binding.btCategory.text = categoryList[position]
                binding.btCategory.background =
                    context.getDrawable(R.drawable.bg_unselected_button)
                binding.btCategory.setTextColor(context.getColor(R.color.secondary_textColor))
                if (selectedItemPosition == position) {
                    binding.btCategory.background =
                        context.getDrawable(R.drawable.bg_selected_button)
                    binding.btCategory.setTextColor(context.getColor(R.color.whiteColor))
                }

                binding.btCategory.setOnClickListener {
                    val previousItem: Int = selectedItemPosition
                    selectedItemPosition = position
                    notifyItemChanged(previousItem)
                    notifyItemChanged(position)
                    itemClick.invoke(binding.btCategory.text.toString())
                }
            }
        }

        override fun getItemCount(): Int {
            return categoryList.size
        }
    }


}

