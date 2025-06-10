package com.amhue.hman.Services;

import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void addUser(Users user) {
        Optional<Users> userDb =  usersRepository.findByEmail(user.getEmail());

        if (userDb.isEmpty()) {
            usersRepository.save(user);
        } else {
            throw new IllegalStateException("User already present!");
        }
    }
}
