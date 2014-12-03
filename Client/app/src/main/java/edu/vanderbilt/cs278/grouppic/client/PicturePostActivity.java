package edu.vanderbilt.cs278.grouppic.client;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import org.magnum.videoup.client.R;


import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;

public class PicturePostActivity extends Activity {
	private static final int PHOTO_REQUEST_CODE = 20;
	private boolean hasSelectedPhoto = false;
    private Bitmap currentImage;

    @InjectView(R.id.lv_friend_list)
	protected ListView friendList;

    @InjectView(R.id.iv_selected_image)
	protected ImageView picture;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_post);


        ButterKnife.inject(this);
		
		String[] friends = {"My Friends"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
		
		friendList.setAdapter(adapter);
	}

	
	public void selectPicture(View v){
		Log.d("PicturePost", "SELECT");
		Intent photoIntent = new Intent(Intent.ACTION_PICK);
		photoIntent.setType("image/*");
		startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
	}
	
	public void sendPicture(View v){
		if(!hasSelectedPhoto){
			Toast.makeText(this, "Please select a picture", Toast.LENGTH_LONG).show();
			return;
		}
		Log.d("PicPostAct", "SEND");
        final PictureSvcApi svc = PictureSvc.getOrShowLogin(this);

        if (svc != null) {

            CallableTask.invoke(new Callable<Picture>() {
                @Override
                public Picture call() throws Exception {
                    Random r = new Random();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    currentImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    Picture p = new Picture("Current Test User", new Date().getTime(), new ArrayList<String>(),
                            new ArrayList<Caption>(), byteArray);
                    p.setId(r.nextLong());
                    Log.d("Send Picture", p.toString() + " id: " + p.getId());
                    return svc.sendPicture(p);
                }
            }, new TaskCallback<Picture>() {
                @Override
                public void success(Picture v) {
                    // Log.d("Success", "Added Picture " + v.toString());
                }

                @Override
                public void error(Exception e) {

                }
            });
        }
		finish();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
		if(requestCode == PHOTO_REQUEST_CODE){
			if(resultCode == RESULT_OK){
				Uri image = returnedIntent.getData();
				try {
					InputStream imageStream = getContentResolver().openInputStream(image);
					Bitmap myImage = BitmapFactory.decodeStream(imageStream);
                    currentImage = myImage;
					picture.setImageBitmap(myImage);
					hasSelectedPhoto = true;
				} catch (FileNotFoundException e) {
					Log.e("PicturePost", "Invalid Photo");
				}
			}
		}
	}
}
