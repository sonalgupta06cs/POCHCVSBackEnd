package com.poc.hcvs.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.hcvs.ws.model.UserEntity;
import com.poc.hcvs.ws.model.UsersNameResult;
import com.poc.hcvs.ws.repository.UserRepository;
import com.poc.hcvs.ws.service.HCVSUserService;
import com.poc.hcvs.ws.shared.dto.UserDto;

@Service
public class HCVSUserServiceImpl implements HCVSUserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserEntity> findAll() {
		
		List<UserEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;

	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity  = mapper.map(userDto, UserEntity.class);
		UserEntity storedUserResponse = userRepository.save(userEntity);
		UserDto userResponse = mapper.map(storedUserResponse, UserDto.class);

		return userResponse;
	}

	@Override
	public List<UserDto> getAllUsersName() {
		
		List<UsersNameResult> userNamesList = userRepository.findUserNames();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		for (UsersNameResult u : userNamesList) {
			UserDto userDto = new UserDto();
			userDto.setId(u.getId());
			userDto.setFullName(u.getFullName());
			userDtoList.add(userDto);
		}
		
		return userDtoList;
	}

}
