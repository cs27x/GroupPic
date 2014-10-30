package edu.vanderbilt.cs278.grouppic.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by andrewbachman on 10/28/14.
 *
 * Class to store the information for a
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String user;
    private int votes;
    private String content;

    public Comment() {
        this.user = "";
        this.votes = 0;
        this.content = "";
    }
    public Comment(String user, String content) {
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

    public void upvote() { ++votes; }
    public void downvote() { --votes; }

}
