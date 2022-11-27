package com.serviceImpl;

import com.DAO.AccountDAO;
import com.DTO.AccountDTO;
import com.entity.Account;

import com.pojo.LoginRequest;
import com.service.AccountService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountDAO accountDAO;
    private final Convert convert;

    private final PasswordEncoder passwordEncoder;
    @Override
    public List<AccountDTO> findAll() {
        return null;
    }

    @Override
    public AccountDTO findById(String username) {
        return convert.toDto(accountDAO.findById(username).get(),AccountDTO.class);
    }

    @Override
    public Account existsAccount(LoginRequest loginRequest){
        if(!accountDAO.existsById(loginRequest.getUsername()))
            return null;
       return accountDAO.findById(loginRequest.getUsername()).get();
    }

    @Override
    public Account getUser(String username){
        return accountDAO.findById(username).get();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public AccountDTO create(AccountDTO AccountDto) {
        Account newAccount = convert.toEntity(AccountDto, Account.class);
        AccountDTO AccountDto2 = convert.toDto(accountDAO.save(newAccount), AccountDTO.class);
        return AccountDto2;
    }

    @Override
    public AccountDTO update(AccountDTO accountDto) {
        Account account = convert.toEntity(accountDto,Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return convert.toDto(accountDAO.save(account),AccountDTO.class) ;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Account account = accountDAO.findById(username).get();
//            if(account == null)
//                throw new UsernameNotFoundException("Not found user");
            // Tạo UserDetails từ Account
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            account.getAuthorities().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole().getIdRole())));
            return new User(account.getIdUsername(),passwordEncoder.encode(account.getPassword())  , authorities);
    }
}
