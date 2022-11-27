package com.service;

import com.DTO.AccountDTO;
import com.entity.Account;
import com.pojo.LoginRequest;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAll();
    AccountDTO findById(String username);

    Account existsAccount(LoginRequest loginRequest);

    Account getUser(String username);

    AccountDTO create(AccountDTO AccountDto);
    AccountDTO update(AccountDTO AccountDto);
    void remove(Integer id);
}
