package ru.geekbrains.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.entities.Role;
import ru.geekbrains.boot.repositories.RoleRepository;
import ru.geekbrains.boot.entities.User;
import ru.geekbrains.boot.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
