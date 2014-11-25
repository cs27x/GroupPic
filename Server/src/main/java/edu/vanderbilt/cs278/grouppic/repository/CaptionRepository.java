package edu.vanderbilt.cs278.grouppic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.vanderbilt.cs278.grouppic.client.*;

@RepositoryRestResource(path = "/caption")
public interface CaptionRepository  extends CrudRepository<Caption, Long> {	
	
	// public Collection<String> captions();
	
}
