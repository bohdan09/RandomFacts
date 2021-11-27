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
import retrofit2.awaitResponse
import java.lang.Exception
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getFactByRandomNumberUseCase.execute().awaitResponse()
                if (response.isSuccessful) {
                    val numberFact = response.body()
                    insertNumberFactToDb(numberFact)
                    getAllHistory()
                }
            } catch (e: Exception) {
                _errors.postValue(e.message)
            }

        }
    }

    fun getFactByUserNumber(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getFactByUserNumberUseCase.execute(number = number).awaitResponse()
                if (response.isSuccessful) {
                    val numberFact = response.body()
                    insertNumberFactToDb(numberFact)
                    getAllHistory()
                }
            } catch (e: Exception) {
                _errors.postValue(e.message)
            }
        }
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