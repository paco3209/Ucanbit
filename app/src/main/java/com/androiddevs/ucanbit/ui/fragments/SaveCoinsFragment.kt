package com.androiddevs.ucanbit.ui.fragments

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.ucanbit.R
import com.androiddevs.ucanbit.adapter.CoinsAdapter
import com.androiddevs.ucanbit.ui.CoinsActivity
import com.androiddevs.ucanbit.ui.CoinsViewModel
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.android.synthetic.main.fragment_saved_news.*

class SaveCoinsFragment: Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: CoinsViewModel
    lateinit var coinsAdapter:CoinsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as CoinsActivity).viewModel

        setupRecyclerView()

        coinsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("coin", it)
            }
            findNavController().navigate(
                R.id.action_coinsListFragment_to_coinFragment,
                bundle
            )
        }


        val backgroundColor = ContextCompat.getColor(context!!,R.color.colorDelete)
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {


                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(backgroundColor)
                    .addActionIcon(R.drawable.ic_baseline_delete_24)
                    .create()
                    .decorate()



                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )


            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val coin = coinsAdapter.differ.currentList[position]
                viewModel.deleteCoin(coin)
                Snackbar.make(view, "Successfully deleted job", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveCoin(coin)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }


        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer {
            coinsAdapter.differ.submitList(it)
        })

    }



    private fun setupRecyclerView(){
        coinsAdapter = CoinsAdapter()
        rvSavedNews.apply {
            adapter = coinsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}