package org.magnum.videoup;

import java.io.FileNotFoundException;
import java.io.InputStream;

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

public class PicturePostActivity extends Activity {
	private static final int PHOTO_REQUEST_CODE = 20;
	private boolean hasSelectedPhoto = false;
	private ListView friendList;
	private ImageView picture;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_post);
		
		
		friendList = (ListView) findViewById(R.id.lv_friend_list);
		picture = (ImageView) findViewById(R.id.iv_selected_image);
		
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
					picture.setImageBitmap(myImage);
					hasSelectedPhoto = true;
				} catch (FileNotFoundException e) {
					Log.e("PicturePost", "Invalid Photo");
				}
			}
		}
	}
}
