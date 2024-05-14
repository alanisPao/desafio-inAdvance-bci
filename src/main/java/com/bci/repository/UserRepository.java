package com.bci.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bci.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByEmail(String email);

	Optional<User> findUserByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.token = :token, u.lastLogin = :lastLogin,u.modified = :modified WHERE u.email = :email")
	void updateUser(@Param("email") String email, @Param("token") String token,
			@Param("lastLogin") LocalDateTime localDateTime, @Param("modified") LocalDateTime localDateTime2);
}
