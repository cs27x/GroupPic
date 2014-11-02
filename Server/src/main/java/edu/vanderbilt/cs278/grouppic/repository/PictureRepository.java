package edu.vanderbilt.cs278.grouppic.repository;

import java.util.Collection;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.vanderbilt.cs278.grouppic.client.*;

@RepositoryRestResource(path = "picture")
public interface PictureRepository  extends CrudRepository<Picture, Long>{
	public Collection<Video> findBySender(
			// The @Param annotation tells Spring Data Rest which HTTP request
			// parameter it should use to fill in the "title" variable used to
			// search for Videos
			@Param(VideoSvcApi.TITLE_PARAMETER) String sender);
}
