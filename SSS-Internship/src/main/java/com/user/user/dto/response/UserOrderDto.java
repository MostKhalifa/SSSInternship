package com.user.user.dto.response;

import java.util.List;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrderDto {

	@Id
	private long customerId;
	private String username;
	private String userEmail;
	private List<OrderDto> order;

	
	

}
