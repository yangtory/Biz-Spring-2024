package com.callor.gallery.dao;

import com.callor.gallery.models.ImageVO;

public interface ImageDao extends GenericDao<ImageVO, String> {

	public void create_table(String dummy);

}
