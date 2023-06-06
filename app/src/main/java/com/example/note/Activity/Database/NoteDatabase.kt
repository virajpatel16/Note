package com.example.note.Activity.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.Activity.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract  class NoteDatabase : RoomDatabase() {

    companion object {

        fun getinstance(context: Context): NoteDatabase {
            var db = Room.databaseBuilder(context, NoteDatabase::class.java, "Notes.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return db

        }
    }
    abstract fun notes():NoteDao
}