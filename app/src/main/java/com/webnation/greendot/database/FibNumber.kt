package com.webnation.greendot.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "fib_numbers", foreignKeys = [ForeignKey(
    entity = FibNumberSeed::class,
    parentColumns = arrayOf("_id"),
    childColumns = arrayOf("fib_number_seed_id"),
    onDelete= ForeignKey.CASCADE)])
class FibNumber(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long,
    @ColumnInfo(name = "fib_number_seed_id")
    var fibNumberSeed: Int,
    @ColumnInfo(name = "input_number")
    var inputNumber: Int,
    @ColumnInfo(name = "fib_number")
    var fibNumber: Int = 0,
    @ColumnInfo(name = "time_to_calc")
    var timeToCalculate: Long = 0L
)

data class PartialFibNumber(
    @ColumnInfo(name = "fib_number_seed_id")
    var fibNumberSeed: Long,
    @ColumnInfo(name = "input_number")
    var inputNumber: Int,
    @ColumnInfo(name = "fib_number")
    val fibNumber: Int?,
    @ColumnInfo(name = "time_to_calc")
    var timeToCalculate: Long = 0L
)