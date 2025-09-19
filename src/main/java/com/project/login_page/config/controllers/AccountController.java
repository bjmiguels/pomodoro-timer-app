package com.project.login_page.config.controllers;

import com.project.login_page.dto.UpdateDailyStreakDTO;
import com.project.login_page.dto.UpdateStudyTimeDTO;
import com.project.login_page.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PatchMapping("/{username}/update-offensive")
    public ResponseEntity<String> updateDailyStreak(@PathVariable String username, @RequestBody UpdateDailyStreakDTO updateDailyStreakDTO){
        int newOffensive = updateDailyStreakDTO.getNewOffensive();

        accountService.updateDailyStreak(username, updateDailyStreakDTO);

        return ResponseEntity.ok("Ofensiva foi atualizada!");
    }

    @PatchMapping("/{username}/update-study-time")
    public ResponseEntity<String> updateStudyTime(@PathVariable String username, @RequestBody UpdateStudyTimeDTO updateStudyTimeDTO){
        int newStudyTime = updateStudyTimeDTO.getAddMinutes();

        accountService.updateMinutesStudied(username, updateStudyTimeDTO);

        return ResponseEntity.ok("Minutos de estudos atualizado");
    }
}
