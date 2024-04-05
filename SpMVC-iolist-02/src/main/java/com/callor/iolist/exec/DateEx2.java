package com.callor.iolist.exec;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateEx2 {
	
	public static void main(String[] args) {
		// 객체를 새로 만들지 않고 만들어져있는 칭구로사용해서 메모리 소모가 덜 함 
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-mm-ss");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		String strDate = localDateTime.format(dayFormat);
		String strTime = localDateTime.format(timeFormat);
		System.out.println(strDate);
		System.out.println(strTime);
		
		System.out.println(localDateTime.plusYears(1));
		System.out.println(localDateTime.minusYears(1));
	}

}
