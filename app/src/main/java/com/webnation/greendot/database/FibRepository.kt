package com.webnation.greendot.database

import androidx.lifecycle.LiveData

class FibRepository(val fibDAO: FibDAO) {

    fun insertFibNumber(fibNumber: FibNumber) {
        fibDAO.insertFibPair(fibNumber)
    }

    fun insertPartialFibNumber(partialFibNumber: PartialFibNumber): Long {
        return fibDAO.insertPartialFibNumber(partialFibNumber)
    }

    fun insertPartialFibNumberSeed(partialFibNumberSeed: PartialFibNumberSeed): Long {
        return fibDAO.insertPartialFibNumberSeed(partialFibNumberSeed)
    }

    fun getAllFibNumber() : LiveData<List<FibNumber>> {
        return fibDAO.getAllFibPairs()
    }

    fun getAllTimesForSeeds() : LiveData<List<FibNumberSeedTime>> {
        return fibDAO.getAllFibNumbersWithTime()
    }
}