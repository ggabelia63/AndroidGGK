package com.example.androidfinal.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import okhttp3.internal.Internal.instance

private var INSTANCE: BookDatabase? = null
@Database(entities = [BookDto::class], version = 2, exportSchema = true,
    autoMigrations =  [
    AutoMigration(from = 1,to =2)

]

 )

abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // you can use SQL statements here to add columns or make other changes
                // to the table
                database.execSQL("ALTER TABLE books_table ADD COLUMN subtitle TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE books_table ADD COLUMN price TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE books_table ADD COLUMN image TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getDatabase(context: Context): BookDatabase {

            val db = Room.databaseBuilder(
                context.applicationContext,
                BookDatabase::class.java, "books.db"
            )
                .addMigrations(MIGRATION_1_2) // add this line
                .build()

            INSTANCE = db
            return db
        }
    }
}


//        @Volatile
//        private var INSTANCE: BookDatabase? = null
//
//        fun getDatabase(context: Context): BookDatabase{
//            val tempInstance = INSTANCE
//            if (tempInstance != null)
//            {
//                return tempInstance
//            }
//
//            synchronized(this)
//            {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    BookDatabase::class.java,
//                    "books_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }





