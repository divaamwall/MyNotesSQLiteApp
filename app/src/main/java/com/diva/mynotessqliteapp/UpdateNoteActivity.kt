package com.diva.mynotessqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.diva.mynotessqliteapp.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db =NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)

        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.labelEditTitleEdt.setText(note.title)
        binding.contentEditNoteEdt.setText(note.content)

        binding.saveEditBtn.setOnClickListener {
            val newTitle = binding.labelEditTitleEdt.text.toString()
            val newContent =binding.contentEditNoteEdt.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "Perubahan Disimpan", Toast.LENGTH_SHORT).show()
        }
    }


}