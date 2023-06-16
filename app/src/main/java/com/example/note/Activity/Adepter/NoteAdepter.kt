package com.example.note.Activity.Adepter

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note.Activity.Database.NoteDatabase
import com.example.note.Activity.MainActivity
import com.example.note.Activity.MainActivity.Companion.binding
import com.example.note.Activity.model.Notes
import com.example.note.R
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteAdepter(notelist: List<Notes>) : Adapter<NoteAdepter.Noteholder>() {

    var list = notelist
    lateinit var context: Context
    lateinit var db: NoteDatabase

    class Noteholder(itemView: View) : ViewHolder(itemView) {

        var txttitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var txtnote = itemView.findViewById<TextView>(R.id.txtNote)
        var pinned = itemView.findViewById<ImageView>(R.id.imgPin)
        var cardnote = itemView.findViewById<CardView>(R.id.cardNote)
        var delete = itemView.findViewById<ImageView>(R.id.datadelete)
        var update = itemView.findViewById<ImageView>(R.id.dataupdate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Noteholder {
        context = parent.context
        return Noteholder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )

    }




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Noteholder, position: Int) {
        db = NoteDatabase.getinstance(context)



        holder.apply {
            txttitle.text = list.get(position).title
            txtnote.text = list.get(position).text
            cardnote.setCardBackgroundColor(list.get(position).color)


            delete.setOnClickListener {
                db.notes().deleteNote(list[position])
                MainActivity.update()
            }
            itemView.setOnClickListener {
                var dialog = Dialog(context)
                dialog.setContentView(R.layout.update_item)

                val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a")
                val current = LocalDateTime.now().format(formater)

                var edttitle = dialog.findViewById<EditText>(R.id.edtTitles)
                var edtNote = dialog.findViewById<EditText>(R.id.edtNotes)

                edttitle.setText(list[position].title)
                edtNote.setText(list[position].text)

                dialog.show()


                update.setOnClickListener {
                    var data =
                        Notes(edttitle.text.toString(), edtNote.text.toString(), current,list.get(position).color,false)
                    data.id = list[position].id

                    db.notes().updatedata(data)
                    dialog.dismiss()
                    MainActivity.update()
                }

            }

            if (list.get(position).pin) {
                pinned.setImageResource(R.drawable.unpinned)

            } else {
                pinned.setImageResource(R.drawable.pinned)
            }
            pinned.setOnClickListener {
                if (list.get(position).pin) {
                    var data = Notes(
                        list.get(position).title,
                        list.get(position).text,
                        list.get(position).date,
                        list.get(position).color,
                        false
                    )
                    data.id = list.get(position).id
                    db.notes().updatedata(data)
                } else {
                    var data = Notes(
                        list.get(position).title,
                        list.get(position).text,
                        list.get(position).date,
                        list.get(position).color,
                        true
                    )
                    data.id = list.get(position).id
                    db.notes().updatedata(data)

                }
                MainActivity.update()

            }
        }


    }

    override fun getItemCount(): Int {
        return list.size

    }
}