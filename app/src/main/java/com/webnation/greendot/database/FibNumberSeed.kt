package com.webnation.greendot.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "fib_numbers_seed")
class FibNumberSeed(@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Long,
    @ColumnInfo(name = "seed")
    val seed: Int)

data class PartialFibNumberSeed(
    @ColumnInfo(name = "seed")
    val seed: Int?
)