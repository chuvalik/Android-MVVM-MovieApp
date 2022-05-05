package com.example.movieapp.feature_main_screen.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.movieapp.R
import com.example.movieapp.core.ui.BaseFragment
import com.example.movieapp.databinding.FragmentMainScreenBinding
import com.example.movieapp.feature_main_screen.presentation.adapter.MainScreenNewMoviesAdapter
import com.example.movieapp.feature_main_screen.presentation.utils.Data.categories
import com.example.movieapp.feature_main_screen.presentation.view_model.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {

    @Inject
    lateinit var glide: RequestManager

    private lateinit var newMoviesAdapter: MainScreenNewMoviesAdapter

    private val viewModel: MainScreenViewModel by viewModels()

    private val categoryTitles = ArrayList(categories.keys)
    private val categoryIcons = ArrayList(categories.values)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newMoviesAdapter = MainScreenNewMoviesAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> {
                        event.data.let { data ->
                            newMoviesAdapter.submitList(data)
                        }
                    }
                    is MainScreenEvent.Loading -> {
                        // show skeleton while loading data
                    }
                    is MainScreenEvent.Failure -> {
                        // show snackbar with error and continue to show skeleton
                    }
                    else -> Unit
                }
            }
        }

        binding.rvNewMovies.adapter = newMoviesAdapter

        setupTabLayout(categoryTitles, categoryIcons)
    }

    private fun setupTabLayout(titles: List<String>, icons: List<Int>) {
        titles.forEachIndexed { index, title ->
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(title))

            binding.tabLayout.getTabAt(index)?.apply {
                setCustomView(R.layout.tab_item)
                customView?.findViewById<ImageView>(R.id.tabIcon)
                    ?.setImageResource(icons[index])
                customView?.findViewById<TextView>(R.id.tabText)?.text = title
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainScreenBinding.inflate(inflater, container, false)

}