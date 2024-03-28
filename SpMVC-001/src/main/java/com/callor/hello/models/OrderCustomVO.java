package com.callor.hello.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 주문과 고객정보를 join 하여 리스트를 확인하기 위한 Data class 
 */

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderCustomVO {
	
	private String o_num;
	private String o_date;
	private String o_ccode;
	
	private String c_code;	
	private String c_name;	
	private String c_tel;	

}
