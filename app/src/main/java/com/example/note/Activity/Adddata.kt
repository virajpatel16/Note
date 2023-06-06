package com.example.note.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.note.Activity.Database.NoteDatabase
import com.example.note.Activity.model.Notes
import com.example.note.R
import com.example.note.databinding.ActivityAdddataBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Adddata : AppCompatActivity() {
    lateinit var binding:ActivityAdddataBinding
    lateinit var db:NoteDatabase
    var selectColor = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAdddataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=NoteDatabase.getinstance(this)

        binding.adddate.setOnClickListener {

            var format=DateTimeFormatter.ofPattern("yyyy-mm-dd  HH:MM:SS a")
            var current=LocalDateTime.now().format(format)

            var note= Notes(
                binding.edtTitles.text.toString(),
                binding.edtNotes.text.toString(),current,selectColor,false
            )
            db.notes().Insert(note)
            finish()


        }
        binding.addcolor.setOnClickListener {
             MaterialColorPickerDialog
                 .Builder(this)
                .setTitle("add color")
                .setColorShape(ColorShape.SQAURE)
                .setColorListener { color, colorHex ->
                    binding.addcolor.setCardBackgroundColor(color)
                    selectColor = color
                }
                .show()
        }


    }
}