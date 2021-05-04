package io.github.glailton.mynotes.service

import io.github.glailton.mynotes.model.Note
import io.github.glailton.mynotes.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class NoteService {

    @Autowired
    lateinit var noteRepository: NoteRepository

    fun add(note: Note): Note {
        return noteRepository.save(note)
    }

    fun findAll(): MutableIterable<Note> {
        return noteRepository.findAll()
    }

    fun findById(id: Long): Note {
        // TODO Find a kotlin way to do that
        val note = noteRepository.findById(id)

        if (note.isEmpty) throw NoSuchElementException("Any Notes Found")

        return note.get()
    }

    fun exists(id: Long): Boolean {
        return noteRepository.existsById(id)
    }

    fun update(id: Long, note: Note): Note {
        val safeNote = Note(id, note.title, note.description)
        return noteRepository.save(safeNote)
    }

    fun delete(id: Long) {
        noteRepository.deleteById(id)
    }
}