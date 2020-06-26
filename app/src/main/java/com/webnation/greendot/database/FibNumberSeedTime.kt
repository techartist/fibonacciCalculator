package com.webnation.greendot.database

import androidx.room.Embedded
import androidx.room.Relation

class FibNumberSeedTime {
    @Embedded
    var FibNumberSeed: FibNumberSeed? = null

    @Relation(
        entity = FibNumber::class,
        parentColumn = "_id",
        entityColumn = "fib_number_seed_id"
    )
    var listOfNumbers: List<FibNumber>? = null
}