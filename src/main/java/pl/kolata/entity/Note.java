package pl.kolata.entity;

import pl.kolata.dto.HistoryNoteForm;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class used to save history notes about user's car
 * Created by Aleksander on 2017-06-26.
 */
@Entity
@Table(name = "NOTE")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EVENT_DATE")
    private LocalDate eventDate;
    @Column(name = "SERVICE_COST")
    private Long serviceCost;
    @Column(name = "PARTS_COST")
    private Long partsCost;
    @Column(name = "EVENT_DESC")
    @Lob
    private byte[] noteContent;
    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car owner;

    public Note(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(byte[] noteContent) {
        this.noteContent = noteContent;
    }

    public Car getOwner() {
        return owner;
    }

    public void setOwner(Car owner) {
        this.owner = owner;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Long getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Long serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Long getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(Long partsCost) {
        this.partsCost = partsCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoteValuesFromForm(HistoryNoteForm form){
        this.eventDate = form.getEventDate();
        this.noteContent = form.getEventDescription().getBytes();
        this.serviceCost = form.getServiceCost();
        this.partsCost = form.getPartsCost();
        this.name = form.getTitle();
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eventDate=" + eventDate +
                ", serviceCost=" + serviceCost +
                ", partsCost=" + partsCost +
                ", noteContent=" + Arrays.toString(noteContent) +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
