package com.example.note.Activity.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
data class Notes(
    @ColumnInfo(name = "title")var title:String,
    @ColumnInfo(name = "text")var text:String,
    @ColumnInfo(name ="date")var date:String,
    @ColumnInfo(name = "color")var color:Int,
    @ColumnInfo(name = "pin")var pin:Boolean
)
{
    @PrimaryKey(autoGenerate = true)var id:Int=0
}
