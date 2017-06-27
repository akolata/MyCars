package pl.kolata.entity;

import pl.kolata.dto.NoteDTO;

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
    @Column(name = "NOTE_ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "SERVICE_COST")
    private Long serviceCost;
    @Column(name = "PARTS_COST")
    private Long partsCost;
    @Column(name = "CONTENT")
    @Lob
    private byte[] noteContent;
    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car owner;

    public Note(){}

    public Note(String title, LocalDate date, Long serviceCost, Long partsCost, byte[] noteContent, Car owner) {
        this.title = title;
        this.date = date;
        this.serviceCost = serviceCost;
        this.partsCost = partsCost;
        this.noteContent = noteContent;
        this.owner = owner;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNoteValuesFromForm(NoteDTO form){
        this.date = form.getDate();
        this.noteContent = form.getDescription().getBytes();
        this.serviceCost = form.getServiceCost();
        this.partsCost = form.getPartsCost();
        this.title = form.getTitle();
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", serviceCost=" + serviceCost +
                ", partsCost=" + partsCost +
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
