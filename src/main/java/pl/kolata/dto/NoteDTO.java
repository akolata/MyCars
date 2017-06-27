package pl.kolata.dto;

import org.hibernate.validator.constraints.NotEmpty;
import pl.kolata.entity.Note;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Note DataTransferObject, with validation annotations
 * Created by Aleksander on 2017-06-26.
 */
public
    class NoteDTO {

    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String description;
    @DecimalMin(value = "0")
    @NotNull
    private Long partsCost;
    @DecimalMin(value = "0")
    @NotNull
    private Long serviceCost;

    public NoteDTO(){}

    public NoteDTO(Note note){
        this.id = note.getId();
        this.date = note.getDate();
        this.description = new String(note.getNoteContent());
        this.partsCost = note.getPartsCost();
        this.serviceCost = note.getServiceCost();
        this.title = note.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "NoteDTO{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", partsCost=" + partsCost +
                ", serviceCost=" + serviceCost +
                '}';
    }
}
