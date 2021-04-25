package com.androiddevs.ucanbit.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.adapter.CoinsAdapter
import com.androiddevs.ucanbit.ui.CoinsActivity
import com.androiddevs.ucanbit.ui.CoinsViewModel
import com.androiddevs.ucanbit.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class CoinsListFragment: Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: CoinsViewModel
    lateinit var coinsAdapter: CoinsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as CoinsActivity).viewModel

        setupRecyclerView()

        viewModel.coins.observe(viewLifecycleOwner, Observer {
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
        rvBreakingNews.apply {
            adapter = coinsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}