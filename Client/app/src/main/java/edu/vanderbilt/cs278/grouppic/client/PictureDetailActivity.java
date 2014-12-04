package edu.vanderbilt.cs278.grouppic.client;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

import java.util.concurrent.Callable;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;

/**
 * @author andrewbachman
 *
 * Activity to display the details view of a picture along with the comments for the picture.
 */
public class PictureDetailActivity extends Activity {

    ArrayList<Caption> comments;
    ArrayList<String> content;
    ArrayAdapter<String> listViewAdapter;

    private long currentPicture_;

    @InjectView(R.id.comment_list)
    protected ListView commentList_;

    @InjectView(R.id.detail_imageView)
    protected ImageView img_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        ButterKnife.inject(this);

        // img_.setImageResource(R.drawable.android_wallpaper);

        currentPicture_ = getIntent().getExtras().getLong("picture_id");

        Log.d("ID", "Current Picture ID " + currentPicture_);

        // Debug Create instance of object even without intent passing a bundle
        // currentPicture_ = 2;

        if (currentPicture_ != 0) {
            // refreshCaptions();
            getCurrentPicture();
        }

        content = new ArrayList<String>();

        listViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, content);

        commentList_.setAdapter(listViewAdapter);

        Button capButton = (Button) findViewById(R.id.capButton);
        EditText capText = (EditText) findViewById(R.id.capText);
        capButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText capText = (EditText) findViewById(R.id.capText);
                String newCaption = capText.getText().toString();
                if (newCaption == null) {
                    Toast.makeText(getApplicationContext(), "Please insert a caption", Toast.LENGTH_SHORT);
                }
                else {
                    Caption newCap = new Caption(newCaption);
                    newCap.setPictureId(currentPicture_);
                    //postCaption(newCap);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getCurrentPicture() {
        final PictureSvcApi svc = PictureSvc.getOrShowLogin(this);

        if (svc != null) {
            CallableTask.invoke(new Callable<Picture>() {
                @Override
                public Picture call() throws Exception {
                    return svc.getPictureWithId(currentPicture_);
                }
            }, new TaskCallback<Picture>() {
                @Override
                public void success(Picture result) {
                    Log.d("picture", result.toString());
                    try {
                        byte[] img = result.imageToByteArray();
                        Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
                        img_.setImageBitmap(bmp);
                    } catch (Base64DecodingException e) {
                        Log.e(this.getClass().getName(), e.toString());
                    }


                }

                @Override
                public void error(Exception e) {

                }
            });
        }
    }

    private void refreshCaptions() {
        final PictureSvcApi svc = PictureSvc.getOrShowLogin(this);

        if (svc != null) {
            CallableTask.invoke(new Callable<Collection<Caption>>() {
                                    @Override
                                    public Collection<Caption> call() throws Exception {
                                        return svc.getComments(currentPicture_);
                                    }
                                }, new TaskCallback<Collection<Caption>>() {
                                    @Override
                                    public void success(Collection<Caption> result) {
                                        for (Caption c : result) {
                                            content.add(c.getContent());
                                        }
                                    }

                                    @Override
                                    public void error(Exception e) {

                                        Toast.makeText(
                                                PictureDetailActivity.this,
                                                "Unable to fetch the video list, please login again.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

            );
        }
    }
/*
    private void postCaption(Caption cap) {
        final PictureSvcApi svc = PictureSvc.getOrShowLogin(this);
        final Caption capt = cap;
        if (svc != null) {
            CallableTask.invoke(new Callable<Collection<Caption>>() {
                                    @Override
                                    public Collection<Caption> call() throws Exception {
                                        return svc.postCaption(capt, capt.getId());
                                        //TODO help me!
                                    }
                                }, new TaskCallback<Caption>() {
                                    @Override
                                    public void success(Caption c) {
                                    }

                                    @Override
                                    public void error(Exception e) {

                                        Toast.makeText(
                                                PictureDetailActivity.this,
                                                "error posting caption",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

            );
        }
    }
    */
}
