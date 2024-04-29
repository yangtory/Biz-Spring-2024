package com.callor.gallery.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.dao.GalleryDao;
import com.callor.gallery.dao.ImageDao;
import com.callor.gallery.models.GalleryVO;
import com.callor.gallery.models.ImageVO;
import com.callor.gallery.service.FileUploadService;
import com.callor.gallery.service.GalleryService;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	private final GalleryDao galleryDao;
	private final FileUploadService fileUploadService;
	private final ImageDao imageDao;
	
	public GalleryServiceImpl(GalleryDao galleryDao, FileUploadService fileUploadService, ImageDao imageDao) {
		super();
		this.galleryDao = galleryDao;
		this.fileUploadService = fileUploadService;
		this.imageDao = imageDao;
	}

	@Autowired
	public void create_table() {
		galleryDao.create_table(null);
		imageDao.create_table("");
	}

	@Override
	public List<GalleryVO> selectAll() {
		return galleryDao.selectAll();
	}

	/*
	 * Single 파일을 업로드 했을때 사용하는 method
	 */
	@Override
	public GalleryVO create_gallery(GalleryVO galleryVO, MultipartFile image_file) throws Exception {
		
		galleryVO.setG_origin_image(image_file.getOriginalFilename());
		
		// 파일을 업로드하고 return 받은 변형된 이름을 VO에 셋팅
		String fileName = fileUploadService.fileUpload(image_file);
		galleryVO.setG_up_image(fileName);
		
		// id, 날짜, 시간 setting 대신  실행하기
		setGalleryOptions(galleryVO);
		int ret = galleryDao.insert(galleryVO);
		if(ret > 0) {
			return galleryVO;
		}
		return null;			
	}

	/*
	 * multi 파일을 업로드 했을때 사용할 method
	 * table 2개를 insert 
	 */
	@Transactional
	@Override
	public List<GalleryVO> createGallery(GalleryVO galleryVO, MultipartHttpServletRequest image_files)
			throws Exception {
		setGalleryOptions(galleryVO);
		// 기본 이미지 정보가 없으므로 공백으로
		galleryVO.setG_origin_image("");
		galleryVO.setG_up_image("");

		// mapper 의 selectKey 에서 설정한 g_id 값을 참조할수 있다
		String i_gid = galleryVO.getG_id();
		
		int gRet = galleryDao.insert(galleryVO);
//		String i_gid = galleryVO.getG_id();
		
		List<ImageVO>resultNames = fileUploadService.filesUpload(image_files);
		
		int iRet = imageDao.inserts(i_gid,resultNames);
		return null;
	}

	private void setGalleryOptions(GalleryVO galleryVO) {
		LocalDateTime lt = LocalDateTime.now();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("YYYY-MM-dd");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
	
		galleryVO.setG_id(UUID.randomUUID().toString());
		galleryVO.setG_date(lt.format(date));
		galleryVO.setG_time(lt.format(time));		
		galleryVO.setG_author("wjdduscldrn@naver.com");		
	}

}
