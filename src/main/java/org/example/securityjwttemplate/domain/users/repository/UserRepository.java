package org.example.securityjwttemplate.domain.users.repository;

import java.util.Optional;

import org.example.securityjwttemplate.common.exception.BizException;
import org.example.securityjwttemplate.domain.users.entity.User;
import org.example.securityjwttemplate.domain.users.exception.UserErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	default User findByEmailOrElseThrow(String email) {
		return findByEmail(email).orElseThrow(() -> new BizException(UserErrorCode.NOT_FOUND_USER));
	}

	default User findByIdOrElseThrow(Long id) {
		return findById(id).orElseThrow(() -> new BizException(UserErrorCode.NOT_FOUND_USER));
	}
}
