package io.github.glailton.mynotes.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Note(@Id
           @GeneratedValue
           @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
           val id: Long = 0L,
           var title: String = "",
           var description: String = "") {
    constructor(title: String, description: String): this() {
        this.title = title
        this.description = description
    }
}
