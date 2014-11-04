package edu.vanderbilt.cs278.grouppic.client;

import android.content.Context;
import android.content.Intent;


import retrofit.RestAdapter;

/**
 * Created by andrewbachman on 10/28/14.
 *
 * Singleton class to store the client data. Currently creates a class on first
 *  call of get instance
 *  TODO: Add authentication method to create instance
 */
public class PictureSvc {

    public static final String SERVER = "http://localhost:8080";
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
/*
    public static synchronized PictureSvcApi init(String server, String user,
                                                String pass) {

        videoSvc_ =
                new RestAdapter.Builder()
                        .setEndpoint(server).setLogLevel(RestAdapter.LogLevel.FULL).build()
                        .create(VideoSvcApi.class);

        return videoSvc_;
    }
    */
    public static synchronized PictureSvcApi init() {
        pictureSvc_ = new RestAdapter.Builder().setEndpoint(SERVER)
                .setLogLevel(RestAdapter.LogLevel.FULL).build()
                .create(PictureSvcApi.class);

        return pictureSvc_;
    }
}
