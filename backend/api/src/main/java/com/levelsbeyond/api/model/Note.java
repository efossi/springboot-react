package com.levelsbeyond.api.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.levelsbeyond.api.model.dto.NoteDto;

@Entity
public class Note {

    public Note(){}

    public Note(String body){
        this.body=body;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 4096)
    private String body;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }
    static public NoteDto convertToDto(Note note){
        return new NoteDto(note.getId(),note.getBody());
    }
}
