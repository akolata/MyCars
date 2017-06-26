package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kolata.dto.HistoryNoteForm;
import pl.kolata.entity.Car;
import pl.kolata.entity.Note;
import pl.kolata.repository.CarRepository;
import pl.kolata.repository.NoteRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * A controller class used with History page
 *Created by Aleksander on 2017-06-26.
 */
@Controller
@RequestMapping(value = "/profile/cars/car")
public class HistoryController {

    private static final String HISTORY_PAGE_NAME = "history",
                                REDIRECT_TO_HISTORY_PAGE_URL = "redirect:/profile/cars/car/%s/history";
    private CarRepository carRepository;
    private NoteRepository noteRepository;
    private Car car;

    @Autowired
    public HistoryController(CarRepository carRepository, NoteRepository noteRepository){
        this.carRepository = carRepository;
        this.noteRepository = noteRepository;
    }

    @GetMapping(value = "/{id}/history")
    public String showCarHistory(@PathVariable(name = "id") String id,
                                 Model model){

        car = carRepository.findOne(Long.valueOf(id));
        Set<Note> notes = car.getNotes();
        Set<HistoryNoteForm> notes2 = parseNotesFromDb(notes);
        System.out.println(notes2);

        model.addAttribute("notes",notes2);
        model.addAttribute("historyNoteForm",new HistoryNoteForm());
        model.addAttribute("id",id);

        return HISTORY_PAGE_NAME;
    }

    @PostMapping(value = "/{id}/history")
    public String submitCarHistoryNote(@PathVariable(name = "id") String id,
                                       @Valid HistoryNoteForm form,
                                       BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return HISTORY_PAGE_NAME;
        }

        saveNoteInDb(form);

        return String.format(REDIRECT_TO_HISTORY_PAGE_URL,id);
    }

    private void saveNoteInDb(HistoryNoteForm form){
        Note note = new Note();
        note.setOwner(car);
        note.setNoteValuesFromForm(form);

        noteRepository.saveAndFlush(note);

        car.getNotes().add(note);
        carRepository.saveAndFlush(car);
    }

    private Set<HistoryNoteForm> parseNotesFromDb(Set<Note> carNotes){
        Set<HistoryNoteForm> notes = new HashSet<>();

        for(Note note : carNotes){
            notes.add(new HistoryNoteForm(note));
        }

        return notes;
    }
}
