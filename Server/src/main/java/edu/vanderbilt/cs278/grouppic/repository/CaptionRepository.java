package edu.vanderbilt.cs278.grouppic.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.vanderbilt.cs278.grouppic.client.*;

@RepositoryRestResource(collectionResourceRel="picture", path = "/caption")
public interface CaptionRepository  extends MongoRepository<Caption, Long> {	
	
	// public Collection<String> captions();
	
}
