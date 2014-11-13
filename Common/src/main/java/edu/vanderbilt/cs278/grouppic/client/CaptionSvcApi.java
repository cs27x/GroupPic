package edu.vanderbilt.cs278.grouppic.client;

import java.util.Collection;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;

public interface CaptionSvcApi {
    public static final String CAPTION_PATH = "/caption";

    // public static final String COMMENT_PATH = "/comments";

    // public static final String POST_COMMENT_PATH = CAPTION_PATH + "/comment";

    @GET(CAPTION_PATH)
    public Collection<Caption> getCaptionList();

    @POST(CAPTION_PATH)
    public Response sendCaption(@Body Caption p);
    
    @DELETE(CAPTION_PATH + "/{id}")
    public Void deleteCaption(@Path("id") long id);
}
