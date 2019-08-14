package com.levelsbeyond.api.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.levelsbeyond.api.repository.NoteRepository;
import com.levelsbeyond.api.model.dto.NoteDto;
import com.levelsbeyond.api.model.Note;
import com.levelsbeyond.api.service.NoteService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    @MockBean
    protected NoteService noteService;


    @Before
    public void setUp() {
        Mockito.reset(noteService);
    }

}
