package com.amhue.hman.Repositories;

import java.util.Optional;

import com.amhue.hman.Entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findById(Integer id);
}
