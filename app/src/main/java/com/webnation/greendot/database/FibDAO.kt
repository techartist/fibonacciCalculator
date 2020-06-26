package com.webnation.greendot.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FibDAO {
    @Insert
    fun insertFibPair(fibNumber: FibNumber) : Long

    @Insert(onConflict = OnConflictStrategy.ABORT, entity = FibNumber::class)
    fun insertPartialFibNumber(partialFibNumber: PartialFibNumber): Long

    @Insert(onConflict = OnConflictStrategy.ABORT, entity = FibNumberSeed::class)
    fun insertPartialFibNumberSeed(partialFibNumberSeed: PartialFibNumberSeed): Long

    @Query("SELECT * FROM fib_numbers ORDER BY fib_number")
    fun getAllFibPairs() : LiveData<List<FibNumber>>

    @Transaction
    @Query("SELECT * FROM fib_numbers_seed")
    fun getAllFibNumbersWithTime() : LiveData<List<FibNumberSeedTime>>
}