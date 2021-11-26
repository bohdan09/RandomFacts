package com.example.randomfacts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.example.randomfacts.domain.model.NumbersFact
import com.example.randomfacts.domain.usecase.*
import com.example.randomfacts.ui.adapter.DiffUtilCallBack
import com.example.randomfacts.ui.adapter.MyAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val getFactByUserNumberUseCase: GetFactByUserNumberUseCase,
    private val getFactByRandomNumberUseCase: GetFactByRandomNumberUseCase,
    private val insertNumberFactToDbUseCase: InsertNumberFactToDbUseCase,
    private val getFactByNumberUseCase: GetFactByNumberUseCase,
    private val getAllNumberFactsUseCase: GetAllNumberFactsUseCase
) : ViewModel() {

    var allHistory: LiveData<List<NumbersFact>>? = getAllNumberFactsUseCase.execute()

    private val _historyRecord = MutableLiveData<NumbersFact>()
    var historyRecord: LiveData<NumbersFact> = _historyRecord

    private val _errors = MutableLiveData<String>()
    val errors: LiveData<String> = _errors

    fun getFactByRandomNumber() {
        getFactByRandomNumberUseCase.execute().enqueue(object : Callback<NumbersFact> {
            override fun onResponse(call: Call<NumbersFact>, response: Response<NumbersFact>) {
                val numberFact = response.body()
                insertNumberFactToDb(numberFact)
                getAllHistory()
            }

            override fun onFailure(call: Call<NumbersFact>, t: Throwable) {
                _errors.value = t.message
            }
        })
    }

    fun getFactByUserNumber(number: Int) {
        getFactByUserNumberUseCase.execute(number = number).enqueue(object : Callback<NumbersFact> {
            override fun onResponse(call: Call<NumbersFact>, response: Response<NumbersFact>) {
                val numberFact = response.body()
                insertNumberFactToDb(numberFact)
                getAllHistory()

            }

            override fun onFailure(call: Call<NumbersFact>, t: Throwable) {
                _errors.value = t.message
            }
        })
    }

    private fun insertNumberFactToDb(numberFact: NumbersFact?) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNumberFactToDbUseCase.execute(numberFact!!)
        }
    }

    private fun getAllHistory() {
        allHistory = getAllNumberFactsUseCase.execute()
    }

    fun getHistoryRecordByNumber(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _historyRecord.postValue(getFactByNumberUseCase.execute(number = number))
        }
    }

    fun updateRecyclerView(
        oldList: List<NumbersFact>,
        newList: List<NumbersFact>,
        adapter: MyAdapter
    ) {
        val diffUtilCallBack = DiffUtilCallBack(oldList, newList)
        val different = DiffUtil.calculateDiff(diffUtilCallBack)
        adapter.setFactsList(newList)
        different.dispatchUpdatesTo(adapter)
    }
}