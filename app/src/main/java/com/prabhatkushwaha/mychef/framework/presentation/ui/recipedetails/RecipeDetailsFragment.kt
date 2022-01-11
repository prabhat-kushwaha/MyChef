package com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.prabhatkushwaha.mychef.HomeActivity
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.business.domain.extensions.getListValue
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import com.prabhatkushwaha.mychef.databinding.FragmentRecipeDetailsBinding
import com.prabhatkushwaha.mychef.databinding.ItemIngredientBinding
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state.DetailsFragmentStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.views.UiUtils.Companion.displayWebView
import com.prabhatkushwaha.mychef.framework.presentation.ui.views.UiUtils.Companion.getBitmap
import com.prabhatkushwaha.mychef.framework.presentation.ui.views.getPalette
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.math.abs


@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class RecipeDetailsFragment(viewModelFactory: ViewModelProvider.Factory) :
    BaseFragment<FragmentRecipeDetailsBinding>() {
    private val args: RecipeDetailsFragmentArgs by navArgs()

    private val viewModel: RecipeDetailsViewModel by viewModels {
        viewModelFactory
    }

    private var homeActivity: HomeActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeActivity = activity as HomeActivity
        homeActivity?.disableNavigationBar()

    }

    override fun initialize() {

        setRecipeData()
        setListener()
    }


    private fun setListener() {

        binding.ivLike.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

            } else {

            }
        }
    }


    private fun setRecipeData() {
        val id = args.id
        viewModel.setRecipeDetailsId(id)
        showProgressDialog()
        viewModel.setStateEvent(DetailsFragmentStateEvent.GetRecipeDetailsEvent())
    }

    private fun setUpToolbarStateTitle(title: String?) {
        binding.appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                binding.collapsingToolBarLayout.title = title
            } else {
                binding.collapsingToolBarLayout.title = ""
            }
        })
    }

    private fun updateUI(palette: Palette) {
        binding.collapsingToolBarLayout.setContentScrimColor(
            palette.getMutedColor(
                R.attr.colorPrimary
            )
        )

        homeActivity?.updateStatusBarColor(
            palette.getMutedColor(
                R.attr.colorPrimary
            )
        )
        binding.container.setBackgroundColor(
            palette.getMutedColor(
                R.attr.colorPrimary
            )
        )
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecipeDetailsBinding {
        return FragmentRecipeDetailsBinding.inflate(inflater, container, false)
    }

    override fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            hideProgressDialog()
            viewState?.let {
                it.recipe?.let { recipe ->
                    showRecipeDetails(recipe)
                }
            }
        }

    }

    private fun showRecipeDetails(recipe: RecipeDetailsModel) {

        binding.btReadMore.setOnClickListener {
            recipe.source_url?.let {
                mContext.displayWebView(it)
            }
        }
        binding.ivRecipe.setImageBitmap(args.recipeImage.getBitmap())
        binding.ivRecipe.drawable.getPalette { palette ->
            palette?.let {
                updateUI(palette)
            }
        }
        binding.tvRecipeName.text = recipe.title

        binding.rvIngredients.adapter =
            IngredientAdapter((recipe.ingredients ?: "").getListValue())
        binding.tvByUser.text = StringBuilder().append("by ").append(recipe.publisher).toString()
        setUpToolbarStateTitle(recipe.title)
    }

    override fun onDestroyView() {
        homeActivity?.changeToolBarPrevColor()
        homeActivity?.enableNavigationBar()
        super.onDestroyView()
    }


    class IngredientAdapter(
        private val ingredients: List<String>
    ) : RecyclerView.Adapter<IngredientAdapter.IngredientViewModel>() {

        inner class IngredientViewModel(val binding: ItemIngredientBinding) :
            RecyclerView.ViewHolder(binding.root)


        override fun onCreateViewHolder(parent: ViewGroup, position: Int): IngredientViewModel {
            val binding = ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return IngredientViewModel(
                binding
            )
        }


        override fun onBindViewHolder(holder: IngredientViewModel, position: Int) {
            with(holder) {

                binding.tvIngredientName.text = ingredients[position]

            }
        }

        override fun getItemCount(): Int {
            return ingredients.size
        }
    }

}