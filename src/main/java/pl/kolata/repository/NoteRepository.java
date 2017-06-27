package pl.kolata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kolata.entity.Note;

/**
 * Created by Aleksander on 2017-06-26.
 */
public interface NoteRepository
extends JpaRepository<Note,Long>{

    @Query(value = "SELECT SUM(SERVICE_COST) FROM Note WHERE CAR_ID = :id",nativeQuery = true)
    Float sumCarServiceCost(@Param(value = "id") Long id);

    @Query(value = "SELECT SUM(PARTS_COST) FROM Note WHERE CAR_ID = :id",nativeQuery = true)
    Float sumCarPartsCost(@Param(value = "id") Long id);
}
