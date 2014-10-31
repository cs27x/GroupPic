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

import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.client.PictureSvcApi;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
 * 
 * This integration test sends HTTP calls to the server using the Retrofit library.
 * The server must be running before you launch this test. 
 * 
 * @author jules
 *
 */
public class PictureSvcClientApiTest extends TestCase {

	private final String TEST_URL = "http://localhost:8080";

	private PictureSvcApi picService = new RestAdapter.Builder()
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(PictureSvcApi.class);

	private static final String TEST_IMAGE_1 = "/jackson-poster.jpg";
	private static final String TEST_IMAGE_2 = "jacksonasdfasdf-poster.jpg";

	private Picture pic;
	private byte[] pretendImageData = {0xA, 0xA, 0xA, 0xB, 0xB, 0xB, 0xC, 0xC, 0xC, 0xD};
	
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
		pic = new Picture("test sender", new Date(), new ArrayList<Long>(), null );
		// ImageInputStream iis = ImageIO.createImageInputStream(is);
		// System.out.println(iis.length());
		// pic.setImageFromStream(iis);
		pic.setImage(pretendImageData);
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
