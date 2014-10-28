package edu.vanderbilt.cs278.grouppic.client;

import java.util.Collection;

import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PicturePreview;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by andrewbachman on 10/28/14.
 *
 * Interface for use with the Retrofit library.
 * Provides an API for PictureSvc
 */
public interface PictureSvcApi {

    public static final String PHOTO_PATH = "/video";

    public static final String CAPTION_PATH = "/captions";

    public static final String POST_CAPTION_PATH = PHOTO_PATH + "/caption";

    @GET(PHOTO_PATH)
    public Collection<PicturePreview> getPictureList();

    @POST(PHOTO_PATH)
    public void sendPicture(@Body Picture p);

    @GET(PHOTO_PATH + "/{id}")
    public Picture getPictureWithId(@Path("id") long id);

    @GET(PHOTO_PATH + "/{id}" + CAPTION_PATH)
    public Collection<Caption> getCaption(@Path("id") long id);

    @POST(POST_CAPTION_PATH)
    public void postCaption(@Body Caption c);

}
