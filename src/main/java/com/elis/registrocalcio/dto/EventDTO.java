package com.elis.registrocalcio.dto;

import com.elis.registrocalcio.model.general.Event;
import com.elis.registrocalcio.model.general.UserEvent;
import com.elis.registrocalcio.other.DateUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDTO {

    public Long id;
    public String category;
    public String date;
    public String hour;
    public UserDTO creator;
    public Boolean played;
    public int freeSeats;

    public EventDTO() {
    }

    public EventDTO(Event event){
        this.id = event.getId();
        this.date = DateUtils.getDateFromInstant(event.getDate());
        this.hour = DateUtils.getHourFromInstant(event.getDate().plus(1, ChronoUnit.HOURS));
        this.category = event.getCategory().toString();
        this.creator = new UserDTO(event.getCreator());
        this.played = event.getPlayed();
        int players = event.getPlayers() != null ? event.getPlayers().size() : 0;
        int totalSeats = event.getCategory().numberOfAllowedPlayers();
        this.freeSeats = totalSeats > players ? totalSeats - players : 0;
    }

    public EventDTO(UserEvent userEvent){
        this.id = userEvent.getId();
        this.date = DateUtils.getDateFromInstant(userEvent.getEvent().getDate());
        this.hour = DateUtils.getHourFromInstant(userEvent.getEvent().getDate());
        this.category = userEvent.getEvent().getCategory().toString();
        this.creator = new UserDTO(userEvent.getEvent().getCreator());
        this.played = userEvent.getEvent().getPlayed();
    }
    public EventDTO(String category, String date, UserDTO creator){
        this.category = category;
        this.date = date;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date.toString();
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("category", category)
                .append("date", date)
                .append("creator", creator)
                .toString();
    }
}
