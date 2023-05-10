package com.hoaxify.hoaxify.repository;

import com.hoaxify.hoaxify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

public interface UserRepository extends JpaRepository<User, Long> {
}
