package edu.vanderbilt.cs278.grouppic.client;

import java.util.Collection;

import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PicturePreview;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.DELETE;

/**
 * Created by andrewbachman on 10/28/14.
 *
 * Interface for use with the Retrofit library.
 * Provides an API for PictureSvc
 */
public interface PictureSvcApi {

    public static final String PICTURE_PATH = "/picture";

    public static final String COMMENT_PATH = "/comments";

    public static final String POST_COMMENT_PATH = PICTURE_PATH + "/comment";

    @GET(PICTURE_PATH)
    public Collection<Picture> getPictureList();

    @POST(PICTURE_PATH)
    public void sendPicture(@Body Picture p);

    @GET(PICTURE_PATH + "/{id}")
    public Picture getPictureWithId(@Path("id") long id);

    @GET(PICTURE_PATH + "/{id}" + COMMENT_PATH)
    public Collection<Caption> getComments(@Path("id") long id);

    @POST(POST_COMMENT_PATH)
    public void postComment(@Body Caption c);
    
    @DELETE(PICTURE_PATH + "/{id}")
    public void deletePicture(@Path("id") long id);

}
