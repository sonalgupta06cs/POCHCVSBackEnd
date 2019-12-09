package com.poc.hcvs.ws.service;

import java.util.List;

import com.poc.hcvs.ws.model.UserEntity;
import com.poc.hcvs.ws.shared.dto.UserDto;

public interface HCVSUserService {

	List<UserEntity> findAll();

	UserDto createUser(UserDto userDto);

	List<UserDto> getAllUsersName();

}
