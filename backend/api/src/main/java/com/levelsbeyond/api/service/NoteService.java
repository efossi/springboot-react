package com.levelsbeyond.api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.levelsbeyond.api.repository.NoteRepository;
import com.levelsbeyond.api.model.dto.NoteDto;
import com.levelsbeyond.api.model.Note;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteDto getNote(Long id) {
        return noteRepository.findById(id)
                .map(note -> new NoteDto(note.getId(), 
                    note.getBody()))
                .orElse(null);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public List<NoteDto> getNotes() {
        return noteRepository.findAll()
                .stream()
                .map(note ->Note.convertToDto(note))
                .collect(Collectors.toList());
    }

    public List<NoteDto> getNotes(Pageable sortedBy) {
        return noteRepository.findAll(sortedBy)
                .stream()
                .map(note ->Note.convertToDto(note))
                .collect(Collectors.toList());
    }

    public List<NoteDto> searchNotes(String q,Pageable sortedBy) {
        return noteRepository.findByBobdyLike(q,sortedBy)
                .stream()
                .map(note ->Note.convertToDto(note))
                .collect(Collectors.toList());
    }

    public NoteDto upsertNote(NoteDto noteDto) {

        Note note= noteRepository
        .save(NoteDto.convertToDao(noteDto) );
        
        return Note.convertToDto(note);
    }

}
