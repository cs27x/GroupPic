package edu.vanderbilt.cs278.grouppic.repository;

import java.util.ArrayList;
import java.util.Collection;

public class PictureRepoCaptionInterfaceImpl implements
		PictureRepoCaptionInterface {

	@Override
	public Collection<String> getCommentsForPicture(long picId) {
		// TODO Auto-generated method stub
		ArrayList<String> captions = new ArrayList();
		captions.add("Caption 1");
		captions.add("Caption 2");
		return captions;
	}

}
