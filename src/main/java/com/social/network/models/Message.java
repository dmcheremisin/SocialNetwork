package com.social.network.models;

import com.social.network.dao.Identifiable;

/**
 * Created by Dmitrii on 23.11.2018.
 */
public class Message implements Identifiable {
    private int id;
    private String date;
    private String message;
    private User sender;
    private User receiver;
    private Integer companion;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Integer getCompanion() {
        return companion;
    }

    public void setCompanion(Integer companion) {
        this.companion = companion;
    }
}
