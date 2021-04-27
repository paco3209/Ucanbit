package com.androiddevs.ucanbit.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.adapter.CoinsAdapter
import com.androiddevs.ucanbit.ui.CoinsActivity
import com.androiddevs.ucanbit.ui.CoinsViewModel
import com.androiddevs.ucanbit.util.Constants.Companion.SEARCH_COINS_DELAY
import com.androiddevs.ucanbit.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchCoinsFragment: Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: CoinsViewModel
    lateinit var coinsAdapter: CoinsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as CoinsActivity).viewModel

        setupRecyclerView()

        var job: Job? = null


        btnSearch.setOnClickListener {
            var searchText = etSearch.text.toString().toLowerCase().trim()
            viewModel.getSearchCoins(searchText)
        }


        coinsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("coin", it)
            }
            findNavController().navigate(
                R.id.action_searchCoinsFragment_to_coinFragment,
                bundle
            )
        }


        viewModel.searchCoin.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    hideProgressBar()
                    it.data?.let {
                        coinsAdapter.differ.submitList(it)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    it.message?.let {
                        Log.e("Error","Error loading response")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })

    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        coinsAdapter = CoinsAdapter()
        rvSearchNews.apply {
            adapter = coinsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}