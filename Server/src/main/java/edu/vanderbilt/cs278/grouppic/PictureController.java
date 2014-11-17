package edu.vanderbilt.cs278.grouppic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.vanderbilt.cs278.grouppic.client.PictureSvcApi;
import edu.vanderbilt.cs278.grouppic.repository.Caption;
import edu.vanderbilt.cs278.grouppic.repository.Picture;
import edu.vanderbilt.cs278.grouppic.repository.PictureRepository;

@RestController
public class PictureController implements PictureSvcApi {
	
	@Autowired
	private PictureRepository pictureRepo;
	
	public PictureController(PictureRepository p) {
		pictureRepo = p;
	}
	public PictureController() {
		
	}
	
	@Override
	@RequestMapping(value="/picture", method=RequestMethod.GET)
	@ResponseBody
	public Collection<Picture> getPictureList() {
		return pictureRepo.findAll();
	}

	@Override
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	@ResponseBody
	public Picture sendPicture(@RequestBody Picture p) {
		System.out.println(p.toString());
		return pictureRepo.save(p);
	}

	@Override
	@RequestMapping(value="/picture/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Picture getPictureWithId(@PathVariable("id") long id) {
		return pictureRepo.findOne(id);
	}

	@Override
	@RequestMapping(value="/captions/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Collection<Caption> getComments(@PathVariable("id") long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caption postCaption(Caption c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void deletePicture(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
