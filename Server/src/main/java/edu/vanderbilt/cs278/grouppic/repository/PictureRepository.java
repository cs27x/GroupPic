package edu.vanderbilt.cs278.grouppic.repository;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import edu.vanderbilt.cs278.grouppic.client.*;

@RepositoryRestResource(path = PictureSvcApi.PICTURE_PATH)
public interface PictureRepository  extends CrudRepository<Picture, Long>{

}
