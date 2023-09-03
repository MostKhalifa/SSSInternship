package com.user.user.mapper;

import java.util.List;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.user.user.dto.request.Signup;
import com.user.user.dto.response.UserGetDto;
import com.user.user.dto.response.UserOrderDto;
import com.user.user.entity.*;

@Mapper (componentModel = "spring")
public interface IUserMapper {
	
	UserGetDto entityToDto(User order);

	List<UserGetDto> entityToDtos(List<User> order);

	User dtoToEntity(Signup signup);
	
//	@Mapping(target="orderId",source="order.id")
//	@Mapping(target="orderName",source="order.name")
//	@Mapping(target="orderDetails",source="order.details")
//	List<UserOrderDto> orderDtoToUserOrderDto(List<OrderDto> order);
	
	@Mapping(target="customerId",source="id")
	@Mapping(target="username",source="user.username")
	@Mapping(target="userEmail",source="user.email")
	UserOrderDto userGetDtoToUserOrderDto(UserGetDto user);
	
	
}
