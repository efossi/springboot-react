package com.levelsbeyond.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.levelsbeyond.api.model.Note;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where n.body like %?1%")
    List<Note> findByBobdyLike(String q, Pageable sortedBy);

}