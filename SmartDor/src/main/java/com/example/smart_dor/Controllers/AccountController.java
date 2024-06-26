package com.example.smart_dor.Controllers;


import com.example.smart_dor.Dto.AccountLoginDto;
import com.example.smart_dor.Dto.AuthRequest;
import com.example.smart_dor.Entities.User;
import com.example.smart_dor.Services.AccountSecurityService;
import com.example.smart_dor.Services.IAccountService;
import com.example.smart_dor.Services.JwtService;

import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAccountService accountIService;

    @Autowired
    AccountSecurityService accountSecurityService;



    @PostMapping("/login")
    public AccountLoginDto login(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return accountSecurityService.loginSucces(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @PostMapping("/signup")
    public User signup(@RequestBody com.example.smart_dor.Entities.User user)  throws PrecheckStatusException, TimeoutException, ReceiptStatusException {
        return accountIService.signup(user);
    }
/*
    @GetMapping("/admin")
    public List<AccountLoginDto> getAllAccount(){
        return accountIService.getAllUser();
    }


    @PostMapping("/signup")
    public User signup(@RequestBody User admin) {
        return accountIService.signup(admin);
    }

    @PostMapping("/addEmploye")
    public User addEmplye(@RequestBody User employe) {
        return accountIService.addEmploye(employe);
    }


    @DeleteMapping("/deleteEmploye/{idAccount}")
    public void deleteEmploye(@PathVariable Long idAccount){
        accountIService.deleteEmploye(idAccount);
    }
*/
}
