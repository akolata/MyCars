package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kolata.dto.NoteDTO;
import pl.kolata.entity.Car;
import pl.kolata.entity.Note;
import pl.kolata.repository.CarRepository;
import pl.kolata.repository.NoteRepository;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * A controller class used with History page
 * Created by Aleksander on 2017-06-26.
 */
@Controller
@RequestMapping(value = "/profile/cars/car")
public
    class HistoryController {

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

    /**
     * Response for GET, method search for car and it's notes in db and passes them to the model
     * @param id car id
     * @param model page model
     * @return name of the page with car history
     */
    @GetMapping(value = "/{id}/history")
    public String showCarHistory(@PathVariable(name = "id") String id,
                                 Model model){

        car = carRepository.findOne(Long.valueOf(id));

        model.addAttribute("notes",prepareNotesToDisplayOnPage(car.getNotes()));
        model.addAttribute("noteDTO",new NoteDTO());
        model.addAttribute("id",id);

        return HISTORY_PAGE_NAME;
    }

    /**
     * Response for POST,if new note from form is submitted
     * @param id car id
     * @param form note from form on the page
     * @param bindingResult validation result
     * @return name of the page with car history
     */
    @PostMapping(value = "/{id}/history",params = {"add"})
    public String submitAddCarHistoryNote(@PathVariable(name = "id") String id,
                                          @Valid NoteDTO form,
                                          BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return HISTORY_PAGE_NAME;
        }

        saveNoteInDb(form);

        return String.format(REDIRECT_TO_HISTORY_PAGE_URL,id);
    }

    /**
     * Method called when user will submit note removal
     * @param id car id
     * @param request request
     * @return history car page
     */
    @PostMapping(value = "/{id}/history",params = {"removeIndex"})
    public String submitRemoveCarHistoryNote(@PathVariable(name = "id") String id,
                                             HttpServletRequest request){

        deleteNoteFromDb(request.getParameter("removeIndex"));

        return String.format(REDIRECT_TO_HISTORY_PAGE_URL,id);
    }

    /**
     * Method is saving a new note in db and assigning it to a current car
     * @param form new note values from form
     */
    private void saveNoteInDb(NoteDTO form){
        Note note = new Note();
        note.setOwner(car);
        note.setNoteValuesFromForm(form);

        noteRepository.saveAndFlush(note);

        car.getNotes().add(note);
        carRepository.saveAndFlush(car);
    }

    /**
     * Method is parsing Note objects from db and preparing them to display on page
     * It converts text in byte[] form to String
     * @param carNotes history notes from db
     * @return set of notes but in different class
     */
    private List<NoteDTO> prepareNotesToDisplayOnPage(List<Note> carNotes){
        List<NoteDTO> notes = new ArrayList<>();

        for(Note note : carNotes){
            notes.add(new NoteDTO(note));
        }

        return notes;
    }

    /**
     * Method delete note from db under given index
     * @param noteIndex index of note in car's notes list
     */
    private void deleteNoteFromDb(String noteIndex) {

        Note note = car.getNotes().get(Integer.valueOf(noteIndex));

        car.getNotes().remove(note);
        carRepository.saveAndFlush(car);
        carRepository.flush();

        noteRepository.delete(note);
    }
}
