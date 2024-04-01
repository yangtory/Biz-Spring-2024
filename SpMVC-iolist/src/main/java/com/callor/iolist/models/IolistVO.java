package com.callor.iolist.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class IolistVO {
	
	private int io_seq;
	private String io_date;
	private String io_time;
	private String io_input;
	private String io_pname;
	private int io_price;
	private int io_quan;
	private int io_total;
	
	// 매출 구분
	private int purchase_price;
	private int sale_price;
	private String io_div;
	
	// 총 합계 
	private int total_purchase;
	private int total_sale;	

}
