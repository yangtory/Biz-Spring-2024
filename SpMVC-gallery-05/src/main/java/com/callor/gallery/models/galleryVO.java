package com.callor.gallery.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class galleryVO {
	
	private String g_id;
	private String g_subject;
	private String g_content;
	private String g_writer;
	private String g_password;
	/*
	 * image 업로드 프로젝트의 주의사항
	 * input 태그 name 속성과 같은 이름으로 
	 * VO 칼럼 이름을 만들면 절대 안됨!
	 */
	private String g_image;

}
