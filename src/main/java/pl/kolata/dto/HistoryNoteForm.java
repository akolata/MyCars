package pl.kolata.dto;

import pl.kolata.entity.Note;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Created by Aleksander on 2017-06-26.
 */
public class HistoryNoteForm {

    @NotNull
    private LocalDate eventDate;
    @NotNull
    private String title;
    @NotNull
    private String eventDescription;
    @DecimalMin(value = "0")
    private Long partsCost;
    @DecimalMin(value = "0")
    private Long serviceCost;

    public HistoryNoteForm(){}

    public HistoryNoteForm(Note note){
        this.eventDate = note.getEventDate();
        this.eventDescription = new String(note.getNoteContent());
        this.partsCost = note.getPartsCost();
        this.serviceCost = note.getServiceCost();
        this.title = note.getName();
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Long getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(Long partsCost) {
        this.partsCost = partsCost;
    }

    public Long getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Long serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HistoryNoteForm{" +
                "eventDate=" + eventDate +
                ", title='" + title + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", partsCost=" + partsCost +
                ", serviceCost=" + serviceCost +
                '}';
    }
}
