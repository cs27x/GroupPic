package edu.vanderbilt.cs278.grouppic.integration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

import org.magnum.mobilecloud.video.TestUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;

import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.client.PictureSvcApi;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;


import java.lang.reflect.Type;

/**
 * 
 * This integration test sends HTTP calls to the server using the Retrofit library.
 * The server must be running before you launch this test. 
 * 
 * @author jules
 *
 */
public class PictureSvcClientApiTest extends TestCase {

	private final static String TEST_URL = "http://localhost:8080";
	private static final Gson gson = new GsonBuilder()
		.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
		//.registerTypeHierarchyAdapter(byte[].class, typeAdapter)
		.create();
	
	/**
	 * This class to handle byte streams is taken from: https://gist.github.com/orip/3635246
	 * @author Ori Peleg
	 *
	 */
	/*
	  private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.decode(json.getAsString(), Base64.NO_WRAP);
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
        }
    }
    */
	
	private static final PictureSvcApi picService = new RestAdapter.Builder()
			.setEndpoint(TEST_URL)
			.setLogLevel(LogLevel.FULL)
			.setConverter(new GsonConverter(gson))
			.build()
			.create(PictureSvcApi.class);

	private static final String TEST_IMAGE_1 = "/jackson-poster.jpg";
	private static final String TEST_IMAGE_2 = "jacksonasdfasdf-poster.jpg";

	
	private Picture pic;
	private Caption cap;
	private byte[] pretendImageData = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3};
	
	/**
	 * @author Jejo Koola
	 * This function loads up an image object from our resources folder
	 */
	@Override
	protected void setUp() throws IOException {
		/*InputStream is = this.getClass().getClassLoader().getResourceAsStream(TEST_IMAGE_1);
		// this.getClass().getClassLoader().getR
		if (is == null) {
			throw new IOException("Could not open image file: " + TEST_IMAGE_1);
		}*/	
		pic = new Picture("test sender", new Date().getTime(), new ArrayList<Long>(), new ArrayList<Caption>(), pretendImageData );
		// ImageInputStream iis = ImageIO.createImageInputStream(is);
		// System.out.println(iis.length());
		// pic.setImageFromStream(iis);
		// pic.setImage(pretendImageData);
		cap = new Caption("test caption");
		
		
	}
	
	/**
	 * @author Jejo Koola
	 * loads a picture file from resources and tries to submit it
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPictureCrud() throws Exception {
		
		// Add the pic
		picService.sendPicture(pic);
		
		// We should get back the pic that we added above
		Collection<Picture> pics = picService.getPictureList();
		assertTrue(pics.size() > 0);
		
		for (Picture pic: pics){
			
			
			picService.postCaption(cap, pic.getId());
		}
		
		System.err.println("ORIGINAL: " + pic.toString());
		for (Picture pic: pics) {
			System.err.println(pic.toString());
		}
		assertTrue(pics.contains(pic));
		
		
		for(Picture v : pics){
			picService.deletePicture(v.getId());
		}
		
		pics = picService.getPictureList();
		assertEquals(0, pics.size());
		
		
	}

	/*
	@Test
	public void testVideoFindByTitle() throws Exception {
		
		// Add the video
		videoService.addVideo(video);
		
		Collection<Video> videos = videoService.findByTitle(video.getName());
		assertTrue(videos.size() > 0);
		for(Video v : videos){
			assertEquals(video.getName(), v.getName());
		}
		
		for(Video v : videos){
			videoService.deleteVideo(v.getId());
		}
	
		assertTrue(videoService.findByTitle(video.getName()).size() == 0);
	}
	
	@Test
	public void testVideoFindByDurationLessThan() throws Exception {
		
		// Add the video
		videoService.addVideo(video);
		
		Collection<Video> videos = videoService.findByDurationLessThan(video.getDuration() + 1);
		assertTrue(videos.size() > 0);
		
		long target = video.getDuration() - 1;
		videos = videoService.findByDurationLessThan(target);
		for(Video v : videos){
			assertTrue(v.getDuration() < target);
		}
		
		videos = videoService.findByDurationLessThan(video.getDuration() + 1);
		for(Video v : videos){
			videoService.deleteVideo(v.getId());
		}
	
		videos = videoService.findByDurationLessThan(video.getDuration() + 1);
		assertTrue(videos.size() == 0);
	}*/
}
