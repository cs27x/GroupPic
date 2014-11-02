package edu.vanderbilt.cs278.grouppic.client;

import java.util.Collection;

import edu.vanderbilt.cs278.grouppic.repository.Comment;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PicturePreview;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Multipart;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

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

    /*
    @Multipart
    @POST(PICTURE_PATH)
    public void sendImageFile(@Part("photo") TypedFile photo, @Part("description") String description);
    */
    @POST(PICTURE_PATH)
    public Void sendPicture(@Body Picture p);

	@DELETE(PICTURE_PATH + "/{id}")
	public void deletePicture(@Path("id") long id);
	
    @GET(PICTURE_PATH + "/{id}")
    public Picture getPictureWithId(@Path("id") long id);

    @GET(PICTURE_PATH + "/{id}" + COMMENT_PATH)
    public Collection<Comment> getComments(@Path("id") long id);

    @POST(POST_COMMENT_PATH)
    public void postComment(@Body Comment c);

}
