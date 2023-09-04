package com.example.myapplication.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleartripint.Adapter.RecyclerAdapter
import com.example.myapplication.R
import com.example.myapplication.data.model.Jokes
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.viewModel.MainViewModel
import com.example.myapplication.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var adapter: RecyclerAdapter

    val myTimer= Timer()
    val list = arrayListOf<Jokes>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initRecyclerView()
        initObserver()

    }

    private fun initObserver() {
        mainViewModel.jokes.observe(this) {
            list.add(it)
            if(list.size>10){
                list.removeAt(0);
            }
            mainViewModel.deleteData()
            mainViewModel.addJokesLocally(list)
            showData(list)
        }
        mainViewModel.jokesList.observe(this) {
            list.addAll(it)
            showData(list)
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    private fun getData() {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            mainViewModel.getLocalData()
            myTimer.schedule(object : TimerTask() {
                override fun run() {
                    mainViewModel.getData()
                }
            }, 0, 1000*5)




        } else {
            mainViewModel.getLocalData()

        }
    }

    private fun showData(list: ArrayList<Jokes>) {
        adapter= RecyclerAdapter(list)
        binding.recycler.adapter=adapter
    }

    private fun initRecyclerView() {
        val linearLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )

        binding.recycler.apply {
            layoutManager = linearLayoutManager
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
            myTimer.cancel()
            myTimer.purge();
    }
}