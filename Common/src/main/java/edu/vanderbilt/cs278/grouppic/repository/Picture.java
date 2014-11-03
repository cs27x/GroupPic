package edu.vanderbilt.cs278.grouppic.repository;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by andrewbachman on 10/27/14.
 *
 * Basic object for storing information about a picture message
 */
@Entity
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
    private Date date;

    /**
     * Collection of strings representing the ids of the recipients
     */
    @ElementCollection
    private Collection<Long> recipients;

    /**
     * Either a string of the id of captions, or a change to a list of caption objects
     */
    @ElementCollection
    private Collection<String> captions;

    /**
     * Byte array storing the image.
     * Stored as a byte array to allow sending over HTTP and allow for easier JSON parsing
     */
    private byte[] image;

    public Picture() {

    }

    public Picture(String sender, Date date, Collection<Long> recipients, byte[] image) {
        this.sender = sender;
        this.date = date;
        this.recipients = recipients;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Collection<Long> getRecipients() { return recipients; }

    public void setRecipients(Collection<Long> recipients) { this.recipients = recipients; }

    public void setCaptions(Collection<String> captions) { this.captions = captions; }
    
    public Collection<String> getCaptions() { return captions; }
    
    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }



    /**
     * @author andrewbachman
     *
     * @return The image in the byte array
     */
    public BufferedImage createBufferedImage() throws IOException{
        return ImageIO.read(new ByteArrayInputStream(image));
    }

    /**
     * @author andrewbachman
     *
     * @return A PicturePreview object for the current image
     */
    public PicturePreview createPreview() {
        return new PicturePreview(this.date, this.sender);
    }

}
