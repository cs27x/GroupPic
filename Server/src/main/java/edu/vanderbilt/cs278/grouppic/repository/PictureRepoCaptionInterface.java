package edu.vanderbilt.cs278.grouppic.repository;

import java.util.Collection;

import org.springframework.data.repository.query.Param;

public interface PictureRepoCaptionInterface {
	public Collection<String> getCommentsForPicture(
			@Param("picture_id") long picId);
}
