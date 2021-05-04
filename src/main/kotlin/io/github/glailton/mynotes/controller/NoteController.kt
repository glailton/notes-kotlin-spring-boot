package io.github.glailton.mynotes.controller

import io.github.glailton.mynotes.model.Note
import io.github.glailton.mynotes.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/notes")
class NoteController {

    @Autowired
    lateinit var service: NoteService

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun findAll(): ResponseEntity<MutableIterable<Note>> = ResponseEntity.ok(service.findAll())

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Note> = ResponseEntity.ok(service.findById(id))

    @PostMapping
    fun add(@RequestBody note: Note): ResponseEntity<Note> =  ResponseEntity.ok(service.add(note))

    @PutMapping("{id}")
    fun update(@RequestBody note: Note, @PathVariable id: Long): ResponseEntity<Any> {
        if (service.exists(id)) {
            return ResponseEntity.ok(service.update(id, note))
        }

        return ResponseEntity.badRequest().body("Note could not be found.")
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        if (service.exists(id)) {
            return ResponseEntity.ok(service.delete(id))
        }

        return ResponseEntity.badRequest().body("Note could not be found.")
    }
}