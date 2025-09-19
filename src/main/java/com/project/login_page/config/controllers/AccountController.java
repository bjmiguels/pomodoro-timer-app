package com.project.login_page.config.controllers;

import com.project.login_page.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PatchMapping("/{username}/update-offensive")
    public ResponseEntity<String> updateDailyStreak(@PathVariable String username){

        accountService.updateDailyStreak(username);

        return ResponseEntity.ok("Ofensiva foi atualizada!");
    }

    @PatchMapping("/{username}/update-study-time")
    public ResponseEntity<String> updateStudyTime(@PathVariable String username){

        accountService.updateMinutesStudied(username);

        return ResponseEntity.ok("Minutos de estudos atualizado");
    }
}
