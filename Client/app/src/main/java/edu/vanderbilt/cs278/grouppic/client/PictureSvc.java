package edu.vanderbilt.cs278.grouppic.client;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
public class PictureSvc {

    public static final String SERVER = "http://10.0.2.2:8080";
    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private static PictureSvcApi pictureSvc_;

    public static synchronized PictureSvcApi getOrShowLogin(Context ctx) {
        if (pictureSvc_ != null) {
            return pictureSvc_;
        } else {
            /*
            Intent i = new Intent(ctx, LoginScreenActivity.class);
            ctx.startActivity(i);
            return null;
            */
            return init();
        }
    }

    public static synchronized PictureSvcApi init() {

        // This section is commented out for testing purposes
        // When the server is running it should be replaced
        /*

        pictureSvc_ =  new RestAdapter.Builder()
                .setEndpoint(SERVER)
                .setLogLevel(LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build()

*/
        pictureSvc_ = new TestPictureSvcApi(); // This is an implementation of the API interface for testing
        Log.d("TEST API", "CREATED");
        return pictureSvc_;
    }
}
