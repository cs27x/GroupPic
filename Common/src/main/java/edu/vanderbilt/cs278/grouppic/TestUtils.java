package edu.vanderbilt.cs278.grouppic;

import edu.vanderbilt.cs278.grouppic.repository.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by andrewbachman on 10/29/14.
 */
public class TestUtils {

    private static final String[] USERS = new String[]{"user1", "user2", "user3", "user4"};
    public static final String[] imgResources = new String[] {
            "http://www.digitaltrends.com/wp-content/uploads/2011/04/Google-Android.png",
            "https://developers.google.com/android/images/index_landing_page.png",
            "http://upload.wikimedia.org/wikipedia/commons/1/12/Vanderbilt_Stadium.jpg"
    };

    public static ArrayList<Caption> getComments() {
        ArrayList<Caption> comments = new ArrayList<Caption>();
        for (int i = 0; i < USERS.length; ++i) {
            Caption c = new Caption(USERS[i], "Test Comment " + i);
            comments.add(c);
        }
        return comments;
    }

    synchronized public static Picture getRandPicture() {
        int user = ((int)  Math.rint(Math.random() * 10)) % USERS.length;
        int img = ((int)  Math.rint(Math.random() * 10)) % imgResources.length;

        byte [] i = null;


        try {
            InputStream is = (InputStream) new URL(imgResources[img]).getContent();
            i = readBytes(is);

        } catch (Exception e) {
        }

        return new Picture(USERS[user], new Date(), new ArrayList<Long>(), new ArrayList<Caption>(), i);
    }

    synchronized private static byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }


}
