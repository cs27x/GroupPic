package edu.vanderbilt.cs278.grouppic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.vanderbilt.cs278.grouppic.client.*;

@RepositoryRestResource(path = "/picture")
public interface PictureRepository  extends CrudRepository<Picture, Long> /*, PictureRepoCaptionInterface */{
	public Collection<Picture> findBySender(
			// The @Param annotation tells Spring Data Rest which HTTP request
			// parameter it should use to fill in the "title" variable used to
			// search for Videos
			@Param("sender") String sender);
	
	// public Collection<String> captions();
	
}
