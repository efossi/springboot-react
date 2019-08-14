package com.levelsbeyond.api.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.levelsbeyond.api.model.Note;
public class NoteDto {

    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank 
    @Size(max=4096)
    private String body;

    public NoteDto(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public NoteDto() {

    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public void setBody(String body) {
        this.body=body;
    }
    static public Note convertToDao(NoteDto dto){
        Note dao=new Note(dto.getBody());

        if(null!=dto.getId()){
            dao.setId(dto.getId());   
        }
        return dao;
    }

}