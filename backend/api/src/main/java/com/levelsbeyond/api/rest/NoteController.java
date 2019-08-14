package com.levelsbeyond.api.rest;

import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.levelsbeyond.api.service.NoteService;
import com.levelsbeyond.api.model.dto.NoteDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.levelsbeyond.api.core.NoteApiException;
import com.levelsbeyond.api.core.NoteNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;        
    }

    @GetMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDto> getNotes(@RequestParam(value="max",defaultValue="10") int max,        
        @RequestParam(value="query",defaultValue="") String qString,
        @RequestParam(value="offset",defaultValue="0") int offset,
        @RequestParam(value="sort",defaultValue="id") String sort,
        @RequestParam(value="order",defaultValue = "asc")  String order) {

        try{
            Sort sorting=null;
            if(order.toLowerCase().startsWith("asc")){
                sorting=Sort.by(sort).ascending();
            }else{
                sorting=Sort.by(sort).descending();
            }
            Pageable sortedBy = PageRequest.of(offset, max, sorting);

            List<NoteDto> notes=new ArrayList<NoteDto>();
            if(null==qString || qString.trim().length()==0 ){
                notes=noteService.getNotes(sortedBy);
            }else{
                notes=noteService.searchNotes( qString, sortedBy);
            }
            if(null==notes || notes.isEmpty()){
                throw new NoteNotFoundException();
            }
            return notes;
        }catch(Exception e){
            throw new NoteApiException("Error Retrieving list of notes",e);
        }

    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public NoteDto getNote(@PathVariable Long id) {
        try{
            NoteDto note= noteService.getNote(id);
            if(null==note){
                throw new NoteNotFoundException();
            }else{
                return note;
            }
        }catch(Exception e){
            throw new NoteApiException("Error Retrieving note",e);
        }
    }


    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDto saveNote(@RequestBody @Valid NoteDto newNoteDto) {
        try{
            return noteService.upsertNote(newNoteDto);
        }catch(Exception e){
            throw new NoteApiException("Error saving note",e);
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDto updateNote(@PathVariable Long id,@RequestBody NoteDto newNoteDto) {
        try{
            NoteDto note= noteService.getNote(id);
            if(null==note){
                throw new NoteNotFoundException();
            }
            note.setBody(newNoteDto.getBody());
            return noteService.upsertNote(note);
        }catch(Exception e){
            throw new NoteApiException("Error Updating note",e);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteNote(@PathVariable Long id) {
        try{
            noteService.deleteNote(id);
            return "OK";
        }catch(Exception e){
            throw new NoteApiException("Error Deleting note",e);
        }
    }
}
