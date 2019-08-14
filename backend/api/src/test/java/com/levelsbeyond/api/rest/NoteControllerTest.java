package com.levelsbeyond.api.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.levelsbeyond.api.repository.NoteRepository;
import com.levelsbeyond.api.model.dto.NoteDto;
import com.levelsbeyond.api.model.Note;
import com.levelsbeyond.api.service.NoteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class NoteControllerTest extends AbstractControllerTest {


    @Test
    public void shouldReturnFoundNotes() throws Exception {

        // given
        List<NoteDto> notes = new ArrayList<>();
        // LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);

        notes.add(new NoteDto(2L, "Test body"));

        Sort sorting=Sort.by("id").ascending();

        Pageable sortedBy = PageRequest.of(0, 10, sorting);

        // when
        when(noteService.getNotes(sortedBy)).thenReturn(notes);

        when(noteService.searchNotes("bod",sortedBy)).thenReturn(notes);

        // then
        mockMvc.perform(get("/notes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].body", is("Test body")));

        mockMvc.perform(get("/notes").param("query", "bod").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].body", is("Test body")));

    }

    @Test
    public void shouldAddNote() throws Exception {

        // given
        String noteBody = "{\"body\":\"Test body\"}";
        NoteDto newNote = createNote("Test body");

        // when
        when(noteService.upsertNote(newNote)).thenReturn(newNote);

        // then
        mockMvc.perform(post("/notes")
                .content(noteBody)
                .contentType(APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private NoteDto createNote(String body) {
        NoteDto newNote = new NoteDto();
        newNote.setBody(body);
        return newNote;
    }

}