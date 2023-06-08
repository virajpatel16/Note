package com.example.note.Activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.note.Activity.Adepter.NoteAdepter
import com.example.note.Activity.Database.NoteDatabase
import com.example.note.Activity.model.Notes
import com.example.note.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = "MainActivity"
        var notesdata = ArrayList<Notes>()
        lateinit var binding: ActivityMainBinding
        lateinit var db: NoteDatabase
        fun update() {
            var list = db.notes().Display()
            list = list.reversed()
            notesdata.clear()
            for (l in list) {
                if (l.pin) {
                    notesdata.add(l)
                }
            }
            for (l in list) {
                if (!l.pin) {
                    notesdata.add(l)
                }
            }
            Log.e(TAG, "update: "+list.size )
            binding.rcvNotes.layoutManager =StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.rcvNotes.adapter = NoteAdepter(notesdata)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = NoteDatabase.getinstance(this)

        update()
        binding.fabtnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, Adddata::class.java))


        }


  window.decorView.systemUiVisibility=
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE or  View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor=Color.TRANSPARENT
    }


}


