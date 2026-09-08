package com.roma.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.roma.roman.RomanFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ClockViewModel : ViewModel() {

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    init {
        updateTimeAndDate()
        startClock()
    }

    private fun updateTimeAndDate() {
        val calendar = Calendar.getInstance()
        
        // 12-hour format for time
        val hour = calendar.get(Calendar.HOUR)
        val displayHour = if (hour == 0) 12 else hour
        val minute = calendar.get(Calendar.MINUTE)
        
        val timeStr = "${RomanFormatter.formatTwoDigit(displayHour)}:${RomanFormatter.formatTwoDigit(minute)}"
        _time.value = timeStr

        // Date: day in Roman, month in English, year in Roman
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val month = monthFormat.format(calendar.time).uppercase()
        
        val dateStr = "${RomanFormatter.format(day)} $month ${RomanFormatter.format(year)}"
        _date.value = dateStr
    }

    private fun startClock() {
        viewModelScope.launch {
            while (true) {
                delay(60_000) // Update every minute
                updateTimeAndDate()
            }
        }
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ClockViewModel() as T
        }
    }
}