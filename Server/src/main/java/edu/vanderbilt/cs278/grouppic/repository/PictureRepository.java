package edu.vanderbilt.cs278.grouppic.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface PictureRepository  extends MongoRepository<Picture, Long> /*, PictureRepoCaptionInterface */{
	public Collection<Picture> findBySender(
			// The @Param annotation tells Spring Data Rest which HTTP request
			// parameter it should use to fill in the "title" variable used to
			// search for Videos
			@Param("sender") String sender);
	
}
