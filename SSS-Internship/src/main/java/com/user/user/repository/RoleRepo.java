package com.user.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.user.entity.ERole;
import com.user.user.entity.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
