package edu.vanderbilt.cs278.grouppic.repository;

import java.util.Date;

/**
 * Created by andrewbachman on 10/27/14.
 *
 * Class to hold what is necessary for previewing pictures in the tile view
 */
public class PicturePreview {

    private long id;
    private Date date;
    private String sender;

    public PicturePreview() {}

    /**
     *
     * @param date2 - the date and time the message was send
     * @param sender - the user who sent the message
     */
    public PicturePreview(Date date2, String sender) {
        this.date = date2;
        this.sender = sender;
    }

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }
}
