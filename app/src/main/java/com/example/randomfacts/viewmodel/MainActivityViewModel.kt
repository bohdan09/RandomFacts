package com.example.randomfacts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomfacts.domain.model.NumbersFact
import com.example.randomfacts.domain.usecase.*
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
    private val _numberFact = MutableLiveData<NumbersFact?>()
    val numbersFact: LiveData<NumbersFact?> = _numberFact


    var allHistory: LiveData<List<NumbersFact>>? = getAllNumberFactsUseCase.execute()

    var historyRecord: LiveData<NumbersFact>? = getFactByNumberUseCase.execute(1418)


    fun getFactByRandomNumber() {
        getFactByRandomNumberUseCase.execute().enqueue(object : Callback<NumbersFact> {
            override fun onResponse(call: Call<NumbersFact>, response: Response<NumbersFact>) {
                val numberFact = response.body()
                insertNumberFactToDb(numberFact)
                _numberFact.value = numberFact
            }

            override fun onFailure(call: Call<NumbersFact>, t: Throwable) {
                _numberFact.value = null
            }
        })
    }

    fun getFactByUserNumber(number: Int) {
        getFactByUserNumberUseCase.execute(number = number).enqueue(object : Callback<NumbersFact> {
            override fun onResponse(call: Call<NumbersFact>, response: Response<NumbersFact>) {
                val numberFact = response.body()
                insertNumberFactToDb(numberFact)
                _numberFact.value = numberFact
            }

            override fun onFailure(call: Call<NumbersFact>, t: Throwable) {
                _numberFact.value = null
            }
        })
    }

    private fun insertNumberFactToDb(numberFact: NumbersFact?) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNumberFactToDbUseCase.execute(numberFact!!)
            getAllHistory()
        }
    }

    private fun getAllHistory() {
        allHistory = getAllNumberFactsUseCase.execute()
    }

    fun getHistoryRecordByNumber(number: Int) {
        Log.d("TAG", "setObservable: ${number}")
        viewModelScope.launch(Dispatchers.IO) {
            historyRecord = getFactByNumberUseCase.execute(number = number)
        }
    }
}