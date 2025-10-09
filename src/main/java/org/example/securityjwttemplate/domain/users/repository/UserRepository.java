package org.example.securityjwttemplate.domain.users.repository;

import java.util.Optional;

import org.example.securityjwttemplate.common.exception.BizException;
import org.example.securityjwttemplate.domain.users.entity.User;
import org.example.securityjwttemplate.domain.users.exception.UserErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	boolean existsByEmail(String email);
	default void validateDuplicateEmail(String email) {
		if (existsByEmail(email)) {
			throw new BizException(UserErrorCode.DUPLICATE_USER_EMAIL);
		}
	}

	Optional<User> findByEmail(String email);
	default User findByEmailOrElseThrow(String email) {
		return findByEmail(email).orElseThrow(() -> new BizException(UserErrorCode.NOT_FOUND_USER));
	}

	default User findByIdOrElseThrow(Long id) {
		return findById(id)
				.filter(user -> !user.isDeleted())
				.orElseThrow(() -> new BizException(UserErrorCode.NOT_FOUND_USER));
	}

	boolean existsByNickname(String nickname);
}
