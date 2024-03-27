package com.callor.hello.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomVO {
	private String c_code;	//varchar(5)
	private String c_name;	//varchar(25)
	private String c_tel;	//varchar(15)
}
