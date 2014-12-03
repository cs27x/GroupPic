package edu.vanderbilt.cs278.grouppic.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by andrewbachman on 10/28/14.
 *
 * Class to store the information for a
 */
@Entity
public class Caption {

    @Id
    private long id;

    private long currentPictureId;
    private String user;
    private int votes;
    private String content;
    private long date;
    
    public Caption() { user = new String(""); content = new String(""); }
    
    public Caption(String content) {
    	this.content = content;
    }    
    
    public Caption(String user, String content) {
        this.user = user;
        this.votes = 0;
        this.content = content;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public int getVotes() { return votes; }

    public void setVotes(int votes) { this.votes = votes; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
    
    public long getDate() { return date; }
    
    public void setDate(long date) { this.date = date; }

    public void setPictureId(long id) { this.currentPictureId = id; }
    
    public long getPictureId() {return currentPictureId; } 
    public void upvote() { ++votes; }
    public void downvote() { --votes; }

}
