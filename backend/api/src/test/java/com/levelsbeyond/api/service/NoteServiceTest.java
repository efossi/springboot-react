package com.levelsbeyond.api.service;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import com.levelsbeyond.api.repository.NoteRepository;
import com.levelsbeyond.api.model.dto.NoteDto;
import com.levelsbeyond.api.model.Note;
import com.levelsbeyond.api.service.NoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteServiceTest {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteService noteService;

    String TEST_BODY="Test body";
    Long TEST_ID=100L;
    @Test
    public void shouldCreateNote() {
        Note note = new Note();
        note.setBody(TEST_BODY);

        NoteDto noteDto = noteService.upsertNote(new NoteDto(TEST_ID,TEST_BODY));

        assertNotNull("Note shouldn't be null", noteDto);
        assertThat(noteDto.getBody(), equalTo(TEST_BODY));
    }

    @Test
    public void shouldReturnCreatedNote() {
        Note note = new Note();
        note.setBody(TEST_BODY);

        noteService.upsertNote(new NoteDto(TEST_ID,TEST_BODY));

        List<NoteDto> noteDtos= noteService.getNotes();

        assertNotNull("Note shouldn't be null", noteDtos);
        assertNotNull("Note shouldn't be null", noteDtos.get(0));
        assertThat(noteDtos.get(0).getBody(), equalTo(TEST_BODY));
    }

    @Test
    public void shouldReturnNullForNotExistingNote() {
        NoteDto noteDto = noteService.getNote(123L);

        assertNull(noteDto);
    }
}