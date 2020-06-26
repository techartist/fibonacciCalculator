package com.webnation.greendot.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FibNumber::class, FibNumberSeed::class), version = 1, exportSchema = false)
abstract class FibDatabase:  RoomDatabase(){
    abstract fun fibDao(): FibDAO

    companion object {
        @Volatile
        private var INSTANCE: FibDatabase? = null

        fun getDatabase(context: Context): FibDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FibDatabase::class.java,
                    "fib_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
        fun newTestInstance(context: Context) =
            Room.inMemoryDatabaseBuilder(context, FibDatabase::class.java).build()

    }

}