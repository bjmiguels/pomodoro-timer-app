package com.project.login_page.service;

import com.project.login_page.domain.User;
import com.project.login_page.dto.*;
import com.project.login_page.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(User user){
        String plainPassword = user.getPassword();
        String hashedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Transactional
    public String loginUser(LoginRequestDTO loginRequestDTO){
        String login = loginRequestDTO.getLogin();

        User user = userRepository.findByUsername(login)
                .or(() -> userRepository.findByEmail(login))
                .orElseThrow(() -> new RuntimeException("Usuário ou email não encontrado!"));

        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new RuntimeException("Senha Inválida!");
        }
        return user.getUsername();
    }

    @Transactional
    public void deleteUser(DeleteUserRequestDTO deleteUserRequest){
        String usernameOrEmail = deleteUserRequest.getLogin();
        String password = deleteUserRequest.getPassword();

        User user = userRepository.findByUsername(usernameOrEmail)
                .or(() -> userRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Senha inválida!");
        }

        userRepository.delete(user);

    }

    @Transactional
    public User updateUsername(String currentUsername, UpdateUsernameDTO updateUsername){
        String password = updateUsername.getPassword();
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (updateUsername.getNewUsername() != null && !updateUsername.getNewUsername().isBlank()){
            user.setUsername(updateUsername.getNewUsername());
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Senha inválida");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User updatePassword(String currentUser, UpdatePasswordDTO updatePasswordDTO){
        String actualPassword = updatePasswordDTO.getActualPassword();

        User user = userRepository.findByUsername(currentUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if(!passwordEncoder.matches(actualPassword, user.getPassword())){
            throw new RuntimeException("Senha atual está incorreta!");

        }

        String newPassword = updatePasswordDTO.getNewPassword();

        if(newPassword != null && !newPassword.isBlank()){
            user.setPassword(passwordEncoder.encode(newPassword));
        }else{
            throw new RuntimeException("A senha não pode ser nula!");
        }

        return userRepository.save(user);

    }
}

