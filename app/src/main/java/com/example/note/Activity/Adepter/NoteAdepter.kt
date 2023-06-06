package com.example.note.Activity.Adepter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note.Activity.Database.NoteDatabase
import com.example.note.Activity.MainActivity
import com.example.note.Activity.model.Notes
import com.example.note.R

class NoteAdepter(notelist: List<Notes>) : Adapter<NoteAdepter.Noteholder>() {

    var list = notelist
    lateinit var context: Context
    lateinit var db: NoteDatabase

    class Noteholder(itemView: View) : ViewHolder(itemView) {

        var txttitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var txtnote = itemView.findViewById<TextView>(R.id.txtNote)
        var pinned = itemView.findViewById<ImageView>(R.id.imgPin)
        var cardnote = itemView.findViewById<CardView>(R.id.cardNote)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Noteholder {
        context = parent.context
        return Noteholder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )

    }


    override fun onBindViewHolder(holder: Noteholder, position: Int) {
        db = NoteDatabase.getinstance(context)

        holder.apply {
            txttitle.text = list.get(position).title
            txtnote.text = list.get(position).text
            cardnote.setCardBackgroundColor(list.get(position).color)

            if (list.get(position).pin) {
                pinned.setImageResource(R.drawable.pinned)
            } else {
                pinned.setImageResource(R.drawable.unpinned)
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