package com.webnation.greendot.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.webnation.greendot.R
import com.webnation.greendot.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime

class MainViewModel(application: Application, val fibRepository: FibRepository): AndroidViewModel(application) {
    var whichFragmentWeAreOn = NavigationFragment.FIRST
    private val context = application.applicationContext
    private val _totalTimeOfCalculations = MutableLiveData(0L)
    val totalTimeOfCalculation: LiveData<Long> = _totalTimeOfCalculations

    fun changeFragment() {
        if (whichFragmentWeAreOn == NavigationFragment.FIRST) whichFragmentWeAreOn = NavigationFragment.SECOND
        else whichFragmentWeAreOn = NavigationFragment.FIRST
    }

    fun getButtonText() : String {
        return context.getString(whichFragmentWeAreOn.buttonText)
    }

    fun calculateTotalTimeOfCalculations(list: List<FibNumber>) {
        var value = 0L
        list.forEach {
            value += it.timeToCalculate
            _totalTimeOfCalculations.value = value
        }
    }

    fun calculateTotalTimeOfCalculationsForEachSeed(list: List<FibNumberSeedTime>) : List<SeedTime> {
        fibRepository.getAllTimesForSeeds()
        val listOfSeedTime = arrayListOf<SeedTime>()
        list.forEach { fnst ->
            var value = 0L
            fnst.listOfNumbers?.forEach { value += it.timeToCalculate }
            val seedTime = SeedTime(fnst.FibNumberSeed?.seed ?: 0, value)
            listOfSeedTime.add(seedTime)
        }
        return listOfSeedTime
    }

    fun calculateFibNumber(number : Int) {

        var t1 = 0
        var t2 = 1

        val listNumberTime = arrayListOf<NumberTime>()
        listNumberTime.add(NumberTime(0,0,0))
        for (i in 1..number) {
            val currentTime = OffsetDateTime.now().toInstant().toEpochMilli()
            val sum = t1 + t2
            val calculatedTime = OffsetDateTime.now().toInstant().toEpochMilli()
            val difference = calculatedTime.minus(currentTime)
            val numberTime = NumberTime(i, sum, difference)
            t1 = t2
            t2 = sum
            listNumberTime.add(numberTime)
        }
        insertNewFibNumber(number,listNumberTime)
    }

    fun insertNewFibNumber(seed: Int, listNumberTime: List<NumberTime>) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = fibRepository.insertPartialFibNumberSeed(PartialFibNumberSeed(seed))
            listNumberTime.forEach {
                fibRepository.insertPartialFibNumber(
                    PartialFibNumber(
                        id,
                        it.number,
                        it.fibNumber,
                        it.time
                    ))
            }
        }
    }

}


data class SeedTime (val seed: Int, val time: Long)

data class NumberTime (val number: Int, val fibNumber: Int, val time: Long)

enum class NavigationFragment(val buttonText: Int) {
    FIRST(R.string.summary), SECOND(R.string.back)
}

