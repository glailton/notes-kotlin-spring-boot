package io.github.glailton.mynotes.service


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NoteServiceTest {

    @Autowired
    lateinit var service: NoteService

    @Test
    fun `should provide a list of notes`() {
        //given
        
        //when
        val notes = service.findAll()
        
        //then
        Assertions.assertNotNull(notes)
    }
}