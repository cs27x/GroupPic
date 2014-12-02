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

    public static final String POST_COMMENT_PATH = PICTURE_PATH +"/{id}"+ "/comments";

    @GET(PICTURE_PATH)
    public Collection<Picture> getPictureList();

    @POST(PICTURE_PATH)
    public Picture sendPicture(@Body Picture p);

    @GET(PICTURE_PATH + "/{id}")
    public Picture getPictureWithId(@Path("id") long id);

    @GET(POST_COMMENT_PATH)
    public Collection<Caption> getComments(@Path("id") long id);

    @POST(POST_COMMENT_PATH)
    public Caption postCaption(@Body Caption c);
    
    @POST(POST_COMMENT_PATH +"/{id}"+"/like")
    public Caption likeCaption(@Path ("id") long id);
    
    
    @DELETE(PICTURE_PATH + "/{id}")
    public Void deletePicture(@Path("id") long id);

}
