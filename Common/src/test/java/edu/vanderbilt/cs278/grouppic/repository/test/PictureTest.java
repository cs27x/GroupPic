package edu.vanderbilt.cs278.grouppic.repository.test;

import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PicturePreview;

import java.util.ArrayList;
import java.util.Date;


import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Created by andrewbachman on 10/28/14.
 */
public class PictureTest {
    // TODO: Add tests for the images in the Picture class add actual picture to byte array
    private final Date date = new Date();
    private final Picture picture = new Picture("Test User", date,
            new ArrayList(), new byte[10]);

    @Test
    public void testToPreview() {
        PicturePreview preview = picture.getPreview();

        assertEquals("Test User", preview.getSender());
        // assertEquals(date, preview.getDate());
    }
}


