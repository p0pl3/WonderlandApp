package com.example.toyshop.service;

import com.example.toyshop.entity.User;
import com.example.toyshop.repository.UserRepository;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;


    public void saveOrUpdate(User user) {
        User foundUser = getByEmail(user.getEmail());
        if (foundUser != null) {
            user.setId(foundUser.getId());
        }
        repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User getByEmail(String username) {
        return repository.findByEmail(username).orElse(null);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User update(User user) {
        return repository.save(user);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }
}
