package com.poc.hcvs.ws.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poc.hcvs.ws.model.UserEntity;
import com.poc.hcvs.ws.model.UsersNameResult;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	Optional<UserEntity> findByEmail(String email);
	
	//public static final String FIND_USERS_NAMES = "SELECT fullName FROM users";

	@Query("SELECT NEW com.poc.hcvs.ws.model.UsersNameResult(u.id, u.fullName) FROM Users u")
	//@Query(value="SELECT u.full_Name FROM Users u", nativeQuery = true)
	public List<UsersNameResult> findUserNames();
	
	//@Query(value="SELECT u.id FROM Users u WHERE u.full_name=:fullName", nativeQuery = true)
	//int findIdByFullName(@Param("fullName") String fullName);
	
	public UserEntity findUserByEmail(String userName);

}
