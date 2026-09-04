package com.codehidder.user_demo.repository;

import com.codehidder.user_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByIdAndDeletedIsFalse(Long id);

    List<User> findAllByDeletedIsFalse();

    List<User> findAllByDeletedIsTrue();
}
