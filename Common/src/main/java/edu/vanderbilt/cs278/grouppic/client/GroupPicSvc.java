package edu.vanderbilt.cs278.grouppic.client;

/*import android.content.Context;
import android.content.Intent;
import android.util.Log;*/

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Created by andrewbachman on 10/28/14.
 *
 * Singleton class to store the client data. Currently creates a class on first
 *  call of get instance
 *  TODO: Add authentication method to create instance
 */
public class GroupPicSvc {

    public static final String SERVER = "http://localhost:8080";
    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private static PictureSvcApi pictureSvc_;
    private static CaptionSvcApi captionSvc_;

    public static synchronized PictureSvcApi getPictureSvcApi() {
        if (pictureSvc_ != null) {
            return pictureSvc_;
        } else {            
            pictureSvc_ =  new RestAdapter.Builder()
            .setEndpoint(SERVER)
            .setLogLevel(LogLevel.FULL)
            .setConverter(new GsonConverter(gson))
            .build()
            .create(PictureSvcApi.class);
            return pictureSvc_;
        }
    }
    
    public static synchronized CaptionSvcApi getCaptionSvc() {
        if (captionSvc_ != null) {
            return captionSvc_;
        } else {            
            captionSvc_ =  new RestAdapter.Builder()
            .setEndpoint(SERVER)
            .setLogLevel(LogLevel.FULL)
            .setConverter(new GsonConverter(gson))
            .build()
            .create(CaptionSvcApi.class);
            return captionSvc_;
        }
    }    
}
