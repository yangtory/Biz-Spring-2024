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
	
	/*
	 * io_seq 를 wrapper class Long 으로 선언하기
	 * 변수를 wrapper 클래스로 선언하면 
	 * 이 변수는 null 값을 가질수 있다
	 * 
	 * JSP 에서 el tag 를 사용하여 값을 비교할때
	 * ${empty io_seq ? '없음':'있음'} 와 같이 empty 키워드를 사용하여
	 * 값의 유무를 판단할 수 있다
	 * 
	 * primitive 방식으로 선언하면 
	 * ${io_seq > 0 ? '있음' : '없음'} 처럼 사용을 하는데
	 * 이 때 io_seq 없어서 0 인지, 실제 값이 0 인지 판단하기가 매우 어렵다
	 */
	
	private Long io_seq;		//	BIGIN
	private String io_date;	//	VARCHAR(10)
	private String io_time;	//	VARCHAR(10)
	private String io_input;	//	VARCHAR(1)
	private String io_pname;	//	VARCHAR(30)
	private Integer io_price;	//	INT
	private Integer io_quan;	//	INT
	private Integer io_total;	//	INT

	private String io_inout;
	private int io_iprice;
	private int io_oprice;
	
	private int io_itotal;
	private int io_ototal;
	
}
