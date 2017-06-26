package pl.kolata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kolata.entity.Note;

/**
 * Created by Aleksander on 2017-06-26.
 */
public interface NoteRepository
extends JpaRepository<Note,Long>{
}
