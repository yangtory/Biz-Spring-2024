package com.callor.iolist.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * long, Long : 큰정수형
 * int, Integer : 작은 정수형
 * 
 * 모두 정수형을 취급하는 변수 선언 키워드 이다
 * 여기에서 소문자 키워드는 primitive(기본형) type
 * 		대문자 키워드는 wrapper class type
 * 
 * primitive type 은 숫자형에 null 과 같은 값을 저장할수 없다
 * wrapper class type 은 숫자형 변수에 null 값을 할당할수 있다
 * 
 * 숫자형 변수에 저장된 값이 0 일때, 실제로 0인 값인지, 값이 없는 것인지
 * 구분하기가 어려울때가 있다
 * 이 때 숫자형 wrapper class 를 사용하여 null 값을 취급하기도 한다
 * 
 */

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IolistVO {
	
	private long io_seq;		//	BIGIN
	private String io_date;	//	VARCHAR(10)
	private String io_time;	//	VARCHAR(10)
	private String io_input;	//	VARCHAR(1)
	private String io_pname;	//	VARCHAR(30)
	private Integer io_price;	//	INT
	private Integer io_quan;	//	INT
	private Integer io_total;	//	INT


}
