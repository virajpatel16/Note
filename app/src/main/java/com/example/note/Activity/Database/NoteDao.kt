package com.example.note.Activity.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.Activity.model.Notes

@Dao
interface NoteDao {
    @Insert
    fun Insert(notes:Notes)

    @Query("SELECT * FROM note")
    fun Display() : List<Notes>

    @Update
    fun updatedata(notes: Notes)

    @Delete
    fun Deletedata(notes: Notes)
}