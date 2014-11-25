package edu.vanderbilt.cs278.grouppic.repository;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.google.common.base.Objects;
import com.sun.org.apache.xml.internal.security.utils.Base64;


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
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Caption> captions;

    /**
     * Byte array storing the image.
     * Stored as a byte array to allow sending over HTTP and allow for easier JSON parsing
     */
    private byte[] image;

    public Picture() { 
    	captions = new ArrayList<Caption>();
    	recipients = new ArrayList<Long>();
    	image = new byte[1];
    	image[0] = 0;
    }

    public Picture(String sender, Date date, Collection<Long> recipients, Collection<Caption> captions, byte[] image) {
        this.sender = sender;
        this.date = date;
        this.recipients = recipients;
        this.captions = captions;
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

    public void setCaptions(Collection<Caption> captions) { this.captions = captions; }
    
    public void addCaption(Caption caption) { captions.add(caption); caption.setPicture(this); }
    
    public Collection<Caption> getCaptions() { return captions; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    /**
     * @author andrewbachman
     *
     * @return A PicturePreview object for the current image
     */
    public PicturePreview createPreview() {
        return new PicturePreview(this.date, this.sender);
    }

	/**
	 * 
	 * 
	 */
	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(sender, date, image, recipients);
	}

	/**
	 * Two Videos are considered equal if they have exactly the same values for
	 * their name, url, and duration.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Picture) {
			Picture other = (Picture) obj;
			// Google Guava provides great utilities for equals too!
			boolean sEquals = Objects.equal(sender, other.sender);
			boolean dEquals =  Objects.equal(date, other.date);
			boolean iEquals =  Arrays.equals(image, other.image);
			boolean cEquals =  Arrays.deepEquals(getCaptions().toArray(), other.getCaptions().toArray());
			boolean rEquals =  Arrays.deepEquals(getRecipients().toArray(), other.getRecipients().toArray());
			return sEquals & dEquals & iEquals & cEquals & rEquals;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String ret = "ID: " + this.getId() + 
					 ", SENDER: " + this.getSender() +
					 ", RECIPIENTS: " + this.getRecipients().toString() +
					 ", CAPTIONS: " + this.getCaptions().toString() +
					 ", DATE: " + this.getDate().toString() +
					 ", IMAGE: [" + Base64.encode(image) + "]";
		return ret;
	}
}
