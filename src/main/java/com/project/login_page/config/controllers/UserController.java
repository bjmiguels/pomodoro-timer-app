package com.project.login_page.config.controllers;


import com.project.login_page.domain.User;
import com.project.login_page.dto.DeleteUserRequestDTO;
import com.project.login_page.dto.UpdatePasswordDTO;
import com.project.login_page.dto.UpdateUsernameDTO;
import com.project.login_page.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PatchMapping("/{username}/reset-username")
    public ResponseEntity<String> updateCurrentUsername(@PathVariable String username, @RequestBody UpdateUsernameDTO updateUsernameDTO){
        String newUsername = updateUsernameDTO.getNewUsername();

        userService.updateUsername(username, updateUsernameDTO);

        return ResponseEntity.ok("Username foi alterado para " + newUsername);


    }
    @PatchMapping("/{username}/reset-password")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody UpdatePasswordDTO updatePasswordDTO){

        userService.updatePassword(username, updatePasswordDTO);

        return ResponseEntity.ok("Senha foi alterada com sucesso!");
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCurrentUser(@RequestBody DeleteUserRequestDTO deleteUserRequest){
        String login = deleteUserRequest.getLogin();

        userService.deleteUser(deleteUserRequest);

        return ResponseEntity.ok("Usuário " + login + " deletado com sucesso!");

    }

}
