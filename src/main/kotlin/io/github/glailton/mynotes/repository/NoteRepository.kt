package io.github.glailton.mynotes.repository

import io.github.glailton.mynotes.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository: CrudRepository<Note, Long> {

    fun findByTitle(title: String): Note
}