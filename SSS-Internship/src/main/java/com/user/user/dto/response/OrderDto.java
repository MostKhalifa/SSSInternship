package com.user.user.dto.response;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

	@Id
	private Long id;
	private String name;
	private String details;

}
