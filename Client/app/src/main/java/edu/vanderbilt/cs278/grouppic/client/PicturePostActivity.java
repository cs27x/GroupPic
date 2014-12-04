package edu.vanderbilt.cs278.grouppic.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;







import butterknife.ButterKnife;
import butterknife.InjectView;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.client.UserSvcApi;

public class PicturePostActivity extends Activity {
	private static final int PHOTO_REQUEST_CODE = 20;
	private static final int CAMERA_REQUEST_CODE = 50;
	private static final String TAG = "PicturePost";
	private boolean hasSelectedPhoto = false;
//	private EditText friendList;
//	private EditText username;
//	private ImageView picture;
	private Bitmap myImage = null;
    private Bitmap currentImage = null;
	private String filePath;

    @InjectView(R.id.lv_friend_list)
	protected EditText friendList;

    @InjectView(R.id.iv_selected_image)
	protected ImageView picture;
	
	//@InjectView(R.id.et_user)
	//protected EditText username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture_post);


        ButterKnife.inject(this);
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
		final String user = "user";
		if(user.length()==0){
			Toast.makeText(this, "Input a username", Toast.LENGTH_LONG).show();
			return;
		}
		final ArrayList<String> recipients = getRecipients();
		if(recipients.size() == 0){
			Toast.makeText(this, "Please specify the recipients", Toast.LENGTH_LONG).show();
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
                    System.err.println(byteArray);
                    Picture p = new Picture(user, new Date().getTime(), recipients,
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
	
	/*Creates the appropriate intent to open up the camera app and take a picture
	 * 
	 * Necessary in the intent is an output file where the picture is saved
	 */
	private Intent getCameraIntent(){
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File imgFile = createImageFile();
		if(imgFile == null){
			return null;
		}
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));
		return cameraIntent;
	}
	
	/*
	 * Makes a file to be stored on the phone in external storage
	 * This is where we'll place the image we take
	 */
	private File createImageFile(){
		File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //makes a unique filename
		String nameFile = "JPEG_" + timeStamp + "_GP"; //GP tag for groupPic
		
		File imgFile;
		try{
			imgFile = File.createTempFile(nameFile, ".jpg", directory);
		}catch(IOException e){
			return null;
		}
		filePath = imgFile.getAbsolutePath();
		return imgFile;
	}
	
	/*
	 * Launches the Camera app and takes a picture, then returns to the current activity
	 * 
	 */
	public void takePicture(View v){
		Log.d(TAG, "CAMERA");
		Intent cameraIntent = getCameraIntent();
		if(cameraIntent == null){
			Toast.makeText(this, "Failed to launch camera", Toast.LENGTH_LONG).show();
			return;
		}
		startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
		if(requestCode == PHOTO_REQUEST_CODE){
			handleSelectPhoto(resultCode, returnedIntent);
		}
		else if(requestCode == CAMERA_REQUEST_CODE){
			handleCameraResult(resultCode, returnedIntent);
		}
	}
	
	/*
	 * Finds the selected photo, stores it, and displays it
	 */
	private void handleSelectPhoto(int resultCode, Intent returnedIntent){
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
	
	/*
	 * Retrieves the taken photo and displays it
	 */
	private void handleCameraResult(int resultCode, Intent returnedIntent){
		if(resultCode == RESULT_OK){
			Log.d(TAG, "OK result");
			myImage = BitmapFactory.decodeFile(filePath);
			picture.setImageBitmap(myImage);
			hasSelectedPhoto = true;
			addToGallery();
		}
	}
	
	//uses the class variable filePath to add the photo to the gallery
		private void addToGallery(){
			Intent galleryIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			File f = new File(filePath);
			galleryIntent.setData(Uri.fromFile(f));
			sendBroadcast(galleryIntent);
		}
		
		private ArrayList<String> getRecipients(){
			ArrayList<String> recipients = new ArrayList<String>();
			String str = friendList.getText().toString();
			String [] friends = str.split(",");
			for(String s:friends){
				s = trimAuxiliaryChars(s);
				if(s.length()>0)
					recipients.add(s);
			}
			return recipients;
		}
		
		private String trimAuxiliaryChars(String s){
			String fixed = "";
			for(int i = 0; i<s.length(); i++){
				if(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))
					fixed += s.charAt(i);
			}
			return fixed;
		}
}
