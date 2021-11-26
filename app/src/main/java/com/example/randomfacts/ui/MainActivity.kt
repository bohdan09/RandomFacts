package com.example.randomfacts.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomfacts.App
import com.example.randomfacts.R
import com.example.randomfacts.databinding.ActivityMainBinding
import com.example.randomfacts.ui.adapter.MyAdapter
import com.example.randomfacts.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener {

    private val model: MainActivityViewModel by viewModels {
        (application as App).getAppComponent().mainActivityFactory()
    }
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        adapter = MyAdapter(this)
        setObservable()
        handleButtonClick()
        setAdapter()
    }


    private fun handleButtonClick() {
        with(binding) {
            getFactB.setOnClickListener {
                makeApiRequestByUserNumber()
            }

            getRandomFactB.setOnClickListener {
                makeApiRequestByRandomNumber()
            }
        }
    }

    override fun onItemClick(number: Int) {
        model.getHistoryRecordByNumber(number)
    }

    private fun setObservable() {
        model.allHistory?.observe(this) {
            adapter.setFactsList(list = it)
        }

        model.historyRecord?.observe(this) {
            Log.d("TAG", "setObservable: ${it}")
        }
    }

    private fun setAdapter() {
        with(binding) {
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.adapter = adapter
        }
    }

    private fun makeApiRequestByUserNumber() {
        if (binding.userNumberET.text.isEmpty()) {
            Toast.makeText(this, resources.getString(R.string.isEmpty), Toast.LENGTH_SHORT).show()
        } else {
            model.getFactByUserNumber(binding.userNumberET.text.toString().toInt())
        }
    }

    private fun makeApiRequestByRandomNumber() {
        model.getFactByRandomNumber()
    }


}