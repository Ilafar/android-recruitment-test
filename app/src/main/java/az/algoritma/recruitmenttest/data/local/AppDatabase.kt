package az.algoritma.recruitmenttest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MarketQuoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marketQuoteDao(): MarketQuoteDao
}