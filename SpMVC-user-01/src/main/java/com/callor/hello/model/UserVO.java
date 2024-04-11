package com.callor.hello.model;

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
public class UserVO {
	private String id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String role;

}
