package edu.vanderbilt.cs278.grouppic.repository;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by andrewbachman on 10/27/14.
 *
 * Basic object for storing information about a picture message
 */
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * String representing the username of the sender
     */
    private String sender;

    /**
     * Date the picture was sent
     */
    private long date;

    /**
     * Collection of strings representing the ids of the recipients
     */
    private Collection<Long> recipients;

    /**
     * Either a string of the id of captions, or a change to a list of caption objects
     */
    private Collection<String> captions;

    /**
     * Byte array storing the image.
     * Stored as a byte array to allow sending over HTTP and allow for easier JSON parsing
     */
    private byte[] image;

    public Picture() {

    }

    public Picture(String sender, long date, Collection<Long> recipients, byte[] image) {
        this.sender = sender;
        this.date = date;
        this.recipients = recipients;
        this.image = image;
        this.captions = new ArrayList<String>();
    }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

    public long getDate() { return date; }

    public void setDate(long date) { this.date = date; }

    public Collection<Long> getRecipients() { return recipients; }

    public void setRecipients(Collection<Long> recipients) { this.recipients = recipients; }

    public Collection<String> getCaptions() { return captions; }

    public void setCaptions(Collection<String> captions) { this.captions = captions; }



    /**
     * @author andrewbachman
     *
     * @return The image in the byte array
     */
    public BufferedImage getImage() throws IOException{
        return ImageIO.read(new ByteArrayInputStream(image));
    }

    /**
     * @author andrewbachman
     *
     * @return A PicturePreview object for the current image
     */
    public PicturePreview getPreview() {
        return new PicturePreview(this.date, this.sender);
    }

}
