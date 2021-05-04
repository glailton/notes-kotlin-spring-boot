package io.github.glailton.mynotes.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.glailton.mynotes.model.Note
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest @Autowired constructor( val mockMvc: MockMvc,
                                                 val objectMapper: ObjectMapper) {

    private val baseUrl: String = "/api/v1/notes"

    @Nested
    @DisplayName("GET /api/v1/notes")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetNotes {
        @Test
        fun `should return all notes`() {
            //when/then
            mockMvc.get("$baseUrl")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$") { hasSize<Int>(2)}
                }
        }
    }

    @Nested
    @DisplayName("GET /api/v1/notes{id}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetNote {
        @Test
        fun `should return the note with given id`() {
            //given
            val id = 1

            //when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.title") { value("Nota alterada")}
                    jsonPath("$.description") { value("Teste para o Spring Boot")}
                }
        }

        @Test
        fun `should return Not Found if the note does not exist`() {
            //given
            val id = 10000

            //when/then
            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
    
    @Nested
    @DisplayName("POST /api/v1/notes")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class AddNote {
        
        @Test
        fun `should add the new note`() {
            //given
            val note = Note("Test Note", "This is a Test")

            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(note)
            }

            //then
            performPost.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.title") { value("Test Note")}
                    jsonPath("$.description") { value("This is a Test")}
                }

        }
        
//        @Test
//        fun `should return BAD REQUEST if the note already exists`() {
//            //given
//            val invalidNote = Note(1, "teste", "teste")
//
//            //when
//            val performPost = mockMvc.post(baseUrl) {
//                contentType = MediaType.APPLICATION_JSON
//                content = objectMapper.writeValueAsString(invalidNote)
//            }
//
//            //then
//            performPost.andDo { print() }
//                .andExpect {
//                    status { isBadRequest() }
//                }
//        }
    }
}