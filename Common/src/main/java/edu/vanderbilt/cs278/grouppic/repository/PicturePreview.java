package edu.vanderbilt.cs278.grouppic.repository;

/**
 * Created by andrewbachman on 10/27/14.
 *
 * Class to hold what is necessary for previewing pictures in the tile view
 */
public class PicturePreview {

    private long id;
    private long date;
    private String sender;

    public PicturePreview() {}

    /**
     *
     * @param date - the date and time the message was send
     * @param sender - the user who sent the message
     */
    public PicturePreview(long date, String sender) {
        this.date = date;
        this.sender = sender;
    }

    public long getDate() { return date; }

    public void setDate(long date) { this.date = date; }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }
}
