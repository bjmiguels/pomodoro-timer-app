package com.project.login_page.service;

import com.project.login_page.domain.Account;
import com.project.login_page.domain.User;
import com.project.login_page.dto.UpdateDailyStreakDTO;
import com.project.login_page.dto.UpdateStudyTimeDTO;
import com.project.login_page.repository.AccountRepository;
import com.project.login_page.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Account updateDailyStreak(String currentUsername, UpdateDailyStreakDTO updateDailyStreakDTO){
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Account account = user.getAccount();
        if (account == null) {
            throw new RuntimeException("Conta não encontrada para o usuário " + currentUsername);
        }

        if(updateDailyStreakDTO.getNewOffensive() >= 0){
            account.setDailyStreak(updateDailyStreakDTO.getNewOffensive());
        }

        return accountRepository.save(account);
    }

    @Transactional
    public Account updateMinutesStudied(String currentUsername, UpdateStudyTimeDTO updateStudyTimeDTO){
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Account account = user.getAccount();
        if(account == null){
            throw new RuntimeException("Conta não encontrada para o usuário " + currentUsername);
        }

        if(updateStudyTimeDTO.getAddMinutes() >= 0){
            account.setMinutesStudied(updateStudyTimeDTO.getAddMinutes());
        }

        return accountRepository.save(account);
    }
}
