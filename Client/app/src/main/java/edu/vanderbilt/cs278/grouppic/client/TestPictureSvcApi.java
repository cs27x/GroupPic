package edu.vanderbilt.cs278.grouppic.client;

import java.util.ArrayList;
import java.util.Collection;

import edu.vanderbilt.cs278.grouppic.TestUtils;
import edu.vanderbilt.cs278.grouppic.repository.Comment;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PicturePreview;

/**
 * Created by andrewbachman on 10/29/14.
 *
 * Implementation of the PictureSvcApi with randomly generated Pictures
 *  for use in frontend testing
 */
public class TestPictureSvcApi implements PictureSvcApi {

    public Collection<PicturePreview> getPictureList() {
        return new ArrayList<PicturePreview>();
    }

    public void sendPicture(Picture p) {

    }

    public Picture getPictureWithId(long id) {
        System.out.println("TestAPI::Called Get Picture with id");
        return TestUtils.getRandPicture();
    }

    public Collection<Comment> getComments(long id) {
        System.out.println("TestAPI::Called getComments");
        return TestUtils.getComments();
    }

    public void postComment(Comment c) {

    }

}
