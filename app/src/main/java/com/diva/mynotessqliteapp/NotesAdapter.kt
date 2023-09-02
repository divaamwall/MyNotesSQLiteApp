package com.diva.mynotessqliteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.diva.mynotessqliteapp.databinding.ItemNoteBinding

class NotesAdapter(private var notes: List<Note>, context: Context) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private val db: NotesDatabaseHelper = NotesDatabaseHelper(context)

    class ViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = itemView.findViewById(R.id.titleNote_tv)
        val content : TextView = itemView.findViewById(R.id.contentNote_tv)
        val updateBtn : ImageView = itemView.findViewById(R.id.update_btn)
        val deleteBtn: ImageView = itemView.findViewById(R.id.delete_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content

        holder.updateBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply{
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteBtn.setOnClickListener {
            db.deleteNote(note.id!!)
            refreshNoteData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Berhasil Dihapus", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = notes.size

    fun refreshNoteData(newNotes: List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }
}