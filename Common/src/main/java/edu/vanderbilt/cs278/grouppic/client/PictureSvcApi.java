package edu.vanderbilt.cs278.grouppic.client;

import java.util.Collection;

import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
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

    // public static final String POST_COMMENT_PATH = PICTURE_PATH + "/comment";
    
    public static final String LOGIN_PATH = "/login";
    
    public static final String LOGOUT_PATH = "/logout";

    public static final String POST_COMMENT_PATH = PICTURE_PATH +"/{id}"+ "/comments";

    public static final String TOKEN_PATH = "/oauth/token";

    public static final String PASSWORD_PARAMETER = "password";

    public static final String USERNAME_PARAMETER = "username";

    @GET(PICTURE_PATH)
    public Collection<Picture> getPictureList();

    @POST(PICTURE_PATH)
    public Picture sendPicture(@Body Picture p);

    @GET(PICTURE_PATH + "/{id}")
    public Picture getPictureWithId(@Path("id") long id);

    @GET(POST_COMMENT_PATH)
    public Collection<Caption> getComments(@Path("id") long id);

    @POST(POST_COMMENT_PATH)
    public Caption postCaption(@Body Caption c, @Path ("id") long id);
    
    @POST(POST_COMMENT_PATH +"/{cd}"+"/like")
    public Void likeCaption(@Path ("cd") long id);
    
    
    @DELETE(PICTURE_PATH + "/{id}")
    public Void deletePicture(@Path("id") long id);

}
