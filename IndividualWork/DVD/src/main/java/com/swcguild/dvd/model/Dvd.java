/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvd.model;

import java.time.LocalDate;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Dvd {

    private int id;
    
    @Length(max=50, message="Title must be no more than 50 characters in length.")
    @NotEmpty(message = "You must supply a value for Title.")
    private String title;
    
    @Length(max=10, min=10, message="Release Date must be 8 characters in length in MMDDYYYY format.")
    @NotEmpty(message = "You must supply a value for Release Date.")
    private String releaseDate;
    
    @Length(max=5, message="MPAA Rating must be no more than 5 characters in length.")
    @NotEmpty(message = "You must supply a value for MPAA Rating.")
    private String mpaaRating;
    
    @Length(max=50, message="Director must be no more than 50 characters in length.")
    @NotEmpty(message = "You must supply a value for Director.")
    private String director;
    
    @Length(max=50, message="Studio must be no more than 50 characters in length.")
    @NotEmpty(message = "You must supply a value for Studio.")
    private String studio;
    
    private String note;

    public Dvd(int id, String title,  String releaseDate, String mpaaRating, String director, String studio, String note) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.mpaaRating = mpaaRating;
        this.director = director;
        this.studio = studio;
        this.note = note;

    }
    
    public Dvd(String title,  String releaseDate, String mpaaRating, String director, String studio, String note) {

        this.title = title;
        this.releaseDate = releaseDate;
        this.mpaaRating = mpaaRating;
        this.director = director;
        this.studio = studio;
        this.note = note;

    }

    public Dvd() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate.toString();
    }

    public void setReleaseDate( String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        return true;
    }

}
