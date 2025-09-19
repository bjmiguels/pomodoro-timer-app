package com.project.login_page.service;

import com.project.login_page.domain.Account;
import com.project.login_page.domain.User;
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
    public Account updateDailyStreak(String currentUsername){
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Account account = user.getAccount();
        if (account == null) {
            throw new RuntimeException("Conta não encontrada para o usuário " + currentUsername);
        }

        int currentStreak = account.getDailyStreak();
        account.setDailyStreak(currentStreak + 1);

        return accountRepository.save(account);
    }

    @Transactional
    public Account updateMinutesStudied(String currentUsername){
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Account account = user.getAccount();
        if(account == null){
            throw new RuntimeException("Conta não encontrada para o usuário " + currentUsername);
        }

        long currentTimeStudied = account.getMinutesStudied();
        account.setMinutesStudied(currentTimeStudied + 25);


        return accountRepository.save(account);
    }
}
