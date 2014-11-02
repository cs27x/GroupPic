package edu.vanderbilt.cs278.grouppic.repository;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
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
    // @ElementCollection
    // private Collection<Long> recipients;

    /**
     * Either a string of the id of captions, or a change to a list of caption objects
     */
    // @ElementCollection
    // private Collection<String> captions;

    /**
     * Byte array storing the image.
     * Stored as a byte array to allow sending over HTTP and allow for easier JSON parsing
     */
    // private byte[] image;

    public Picture() {

    }
/*
    public Picture(String sender, long date, Collection<Long> recipients, byte[] image) {
        this.sender = sender;
        this.date = date;
        this.recipients = recipients;
        this.image = image;
        this.captions = new ArrayList<String>();
    }
*/
    public Picture(String sender, Date date, Collection<Long> recipients, byte[] image) {
        this.sender = sender;
        this.date = date;
        // this.recipients = recipients;
        // this.image = image;
        // this.captions = new ArrayList<String>();
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
/*
    public Collection<Long> getRecipients() { return recipients; }

    public void setRecipients(Collection<Long> recipients) { this.recipients = recipients; }

    public Collection<String> getCaptions() { return captions; }

    public void setCaptions(Collection<String> captions) { this.captions = captions; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }
*/


    /**
     * @author andrewbachman
     *
     * @return The image in the byte array
     */
 /*   public BufferedImage getBufferedImage() throws IOException{
        return ImageIO.read(new ByteArrayInputStream(image));
    }
*/
    /**
     * @author andrewbachman
     *
     * @return A PicturePreview object for the current image
     */
    public PicturePreview getPreview() {
        return new PicturePreview(this.date, this.sender);
    }
    
    /**
     * @author Jejo Koola
     * @param is an InputStream object that points to an image file, from which we will 
     * 	read bytes
     * @throws IOException 
     */
    public void setImageFromStream(ImageInputStream imageStream) throws IOException {
    //	image = new byte[(int) imageStream.length()];
    //	imageStream.readFully(image);
    }

}
