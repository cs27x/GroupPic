package edu.vanderbilt.cs278.grouppic.client;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

import edu.vanderbilt.cs278.grouppic.TestUtils;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PicturePreview;

/**
 * Created by andrewbachman on 10/29/14.
 *
 * Implementation of the PictureSvcApi with randomly generated Pictures
 *  for use in frontend testing
 */
public class TestPictureSvcApi implements PictureSvcApi {

    private ArrayList<Picture> pictures;
    private ArrayList<Caption> captions;

    public TestPictureSvcApi() {

        pictures = new ArrayList<Picture>();
        captions = new ArrayList<Caption>();

        for (int i = 0; i < 10; ++i) {
            Picture p = TestUtils.getRandPicture();
            p.setId(i + 1);
            pictures.add(p);
        }
    }

    public Collection<Picture> getPictureList() {
        return pictures;
    }

    public Picture sendPicture(Picture p) {
        p.setId(pictures.size() + 100);
        pictures.add(p);
        return p;
    }

    public Picture getPictureWithId(long id) {
        System.out.println("TestAPI::Called Get Picture with id");
        for (Picture p : pictures) {
            if (p.getId() == id && p.getId() > 100) {
                Log.d("Get Picture", "Found Picture with Id: " + p.toString());
                return p;
            }
        }
        Picture p = TestUtils.getRandPicture();
        Log.d("Get Picture", p.toString());
        return p;
    }

    public Collection<Caption> getComments(long id) {
        System.out.println("TestAPI::Called getComments");
        return TestUtils.getComments();
    }

    public Caption postCaption(Caption c) {
        return new Caption();
    }

    public Void deletePicture(long id) { return null; }

}
